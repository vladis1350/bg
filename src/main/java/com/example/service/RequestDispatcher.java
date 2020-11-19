package com.example.service;

import com.example.buttonHandler.ButtonHandler;
import com.example.buttonHandler.MainMenuButton;
import com.example.buttonHandler.MyInfoButtonHandler;
import com.example.processor.*;
import com.example.enums.BotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RequestDispatcher {
    @Autowired
    MessageService messageService;
    @Autowired
    HelpProcessor helpProcessor;
    @Autowired
    SettingsProcessor settingsProcessor;
    @Autowired
    StartProcessor startProcessor;
    @Autowired
    NoneProcessor noneProcessor;
    @Autowired
    PaymentProcessor paymentProcessor;
    @Autowired
    SendContactProcessor sendContactProcessor;
    @Autowired
    ButtonHandler buttonHandler;
    @Autowired
    MainMenuButton mainMenuButton;
    @Autowired
    MyInfoButtonHandler myInfoButtonHandler;

    private static String state = "0";
    private static String name = "";
    private static String age = "";
    private int messageId;
    private int myMessageId;

    public void dispatch(Update update) {
        switch (getCommand(update)) {
            case HELP:
                messageService.sendMessage(update.getMessage(), helpProcessor.run());
                break;
            case START:
                messageService.sendMessage(update.getMessage(), startProcessor.run());
                break;
            case SETTING:
                messageService.sendMessage(update.getMessage(), settingsProcessor.run());
                break;
            case NONE:
                messageService.sendMessage(update.getMessage(), noneProcessor.run());
                break;
            case SEND_PHONE_NUMBER:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), sendContactProcessor.run());
                break;
            case MAIN_MENU:
                messageService.sendMessageAndKeyboard(update.getMessage(), "Здесь ты можешь увидеть то, чем я могу тебе помочь.\nДавай начнем с ввода твоих данных.\n\nПерейди во вкладку - 'Моя информация'.");
                break;
            case MY_CARTS:
                messageService.sendMessage(myInfoButtonHandler.getButtonMyCartsAndMessage(update.getMessage().getChatId()));
                break;
            case PARTICIPANT_PROGRAMS:
                messageService.sendMessage(buttonHandler.
                        getButtonParticipantProgramsAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case CATALOG:
                messageService.sendMessage(new SendMessage(update.getMessage().getChatId(), "Доп функции")
                        .setReplyMarkup(mainMenuButton.getExtraCatalogKeyboard()));
                break;
            case SEE_CATALOG:
                messageService.sendMessage(buttonHandler.
                        getButtonCatalogAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case SHARES:
                messageService.sendMessage(update.getMessage(), "Акционные товары: \n\n1. Молоко 2.5% - 0.99 BYN\n2. Зефир 'Минский' 200гр. - 2.18 BYN\n3. Эко-сумка 'ТвояТорба' - 9.99 BYN\n");
                break;
            case DELIVERY:
                messageService.sendMessage(buttonHandler.
                        getButtonDeliveryInfoAndMessage(update.getMessage().getChatId(), "Заказ в пути"));
                break;
            case MY_INFORMATION:
                messageService.sendMessage(new SendMessage(update.getMessage().getChatId(), "Доп информация")
                        .setReplyMarkup(mainMenuButton.getExtraMyInfoKeyboard()));
                break;
            case INFORMATION:
                messageService.sendMessage(buttonHandler.
                        getButtonMyInformationAndMessage(update.getMessage().getChatId(), "Выберите действие"));
                break;
            case INFORMATION_CART:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Карта: Платина"));
                break;
            case QUANTITY_BALLS:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Количество баллов: 18 - 9 BYN"));
                break;
            case LIST_BY_CATEGORY:
                messageService.sendMessage(buttonHandler.
                        getButtonCancelToParticipantAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Список:\n\n1. Продовольственные.\n2. Непродовольственные\n"));
                break;
            case POPULAR_PRODUCTS:
                messageService.sendMessage(buttonHandler.
                        getButtonCancelToCategoryProdAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Популярные товары: \n\n1. Товар 1\n2. Товар 2\n3. Товар 3\n"));
                break;
            case CATEGORIZATION:
                messageService.sendMessage(buttonHandler.
                        getButtonCancelToCategoryProdAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Категории: \n\n1. Category one\n2. Category two\n3. Category three\n"));
                break;
            case GET_DISCHARGE:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Выписка: \n\n" + name + "\n" + age);
                break;
            case OPERATIONS:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Операции");
                break;
            case INPUT_DATA:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите ФИО");
                state = "askName";
                break;
            case ASK_AGE:
                messageService.sendMessage(update.getMessage(), "Введите возраст");
                state = "askAge";
                break;
            case GET_BTN_DISCHARGE:
                messageService.sendMessage(buttonHandler.getButtonDischargeAndMessage(update.getMessage().getChatId(), "Можно получить выписку!"));
                break;
            case LEAVE_FEEDBACK:
                messageService.sendMessage(update.getCallbackQuery().getMessage(), "Введите ваш отзыв");
                state = "askFeedback";
                break;
            case FEEDBACK_FILLED:
                messageService.sendMessage(update.getMessage(), "Спасибо за ваш отзыв!");
                break;
            case CANCEL:
                messageService.sendMessage(new SendMessage(update.getMessage().getChatId(), "Главное меню")
                        .setReplyMarkup(mainMenuButton.getMainMenuKeyboard()));
                break;
            case CANCEL_TO_CARD_INFO:
                messageService.sendMessage(myInfoButtonHandler
                        .getButtonCancelToInfoAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Выберите действие"));
                break;
            case STATUS_UP_CARD:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "STATUS UP"));
                break;
            case CLOSE_CARD:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "CLOSE CARD"));
                break;
            case CANCEL_TO_CATALOG:
                messageService.sendMessage(buttonHandler
                        .getCancelToParticipantAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Выберите действие"));
                break;
            case INVITE_FRIEND:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Ссылка на приглашение: https://t.me/BG11_bot"));
                break;
            case SEARCH_BY_GEO:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonCancelAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "SEARCH BY ADDRESS"));
                break;
            case CANCEL_TO_CATEGORY_PRODUCT:
                messageService.sendMessage(buttonHandler
                        .getCancelToCategoryProdAndMessage(update.getCallbackQuery().getMessage().getChatId(), messageId, "Выберите действие"));
                break;
            case MY_DISCOUNT:
                messageService.getQR(messageService.getUserData(update.getMessage()));
                messageService.sendQRCode(update.getMessage().getChatId());
                break;
            case SEND_PAY:
                messageService.sendInvoice(update.getMessage());
                break;
            case BUY_CARD:
                messageService.sendMessage(myInfoButtonHandler.
                        getButtonBuyCardAndMessage(update.getMessage().getChatId()));
                break;
            default:
                messageService.sendMessage(update.getMessage(), "+");
                break;
        }
    }

    private BotCommand getCommand(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                myMessageId = message.getMessageId();
                String msgText = message.getText();
                if (msgText.startsWith(BotCommand.HELP.getCommand())) {
                    return BotCommand.HELP;
                } else if (msgText.startsWith(BotCommand.START.getCommand())) {
                    return BotCommand.START;
                } else if (msgText.startsWith(BotCommand.CANCEL.getCommand())) {
                    return BotCommand.CANCEL;
                } else if (msgText.startsWith(BotCommand.SETTING.getCommand())) {
                    return BotCommand.SETTING;
                } else if (msgText.startsWith(BotCommand.MY_CARTS.getCommand())) {
                    return BotCommand.MY_CARTS;
                } else if (msgText.startsWith(BotCommand.PARTICIPANT_PROGRAMS.getCommand())) {
                    return BotCommand.PARTICIPANT_PROGRAMS;
                } else if (msgText.startsWith(BotCommand.CATALOG.getCommand())) {
                    return BotCommand.CATALOG;
                } else if (msgText.startsWith(BotCommand.SEE_CATALOG.getCommand())) {
                    return BotCommand.SEE_CATALOG;
                } else if (msgText.startsWith(BotCommand.SHARES.getCommand())) {
                    return BotCommand.SHARES;
                } else if (msgText.startsWith(BotCommand.MY_INFORMATION.getCommand())) {
                    return BotCommand.MY_INFORMATION;
                } else if (msgText.startsWith(BotCommand.INFORMATION.getCommand())) {
                    return BotCommand.INFORMATION;
                } else if (msgText.startsWith(BotCommand.DELIVERY.getCommand())) {
                    return BotCommand.DELIVERY;
                } else if (msgText.startsWith(BotCommand.MY_DISCOUNT.getCommand())) {
                    return BotCommand.MY_DISCOUNT;
                } else if (state.equals("askName")) {
                    name = msgText;
                    return BotCommand.SEND_PAY;
                } else if (state.equals("askAge")) {
                    age = msgText;
                    return BotCommand.GET_BTN_DISCHARGE;
                } else if (state.equals("askFeedback")) {
                    return BotCommand.FEEDBACK_FILLED;
                } else if (msgText.startsWith("Нет")) {
                    return BotCommand.MAIN_MENU;
                } else if (msgText.startsWith("Привет я бот")) {
                    return BotCommand.MAIN_MENU;
                }
            } else if (message != null && message.hasContact()) {
                return BotCommand.MAIN_MENU;
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery buttonQuery = update.getCallbackQuery();
            messageId = buttonQuery.getMessage().getMessageId();
            if (buttonQuery.getData().equals("buttonMyCarts")) {
                return BotCommand.INFORMATION_CART;
            } else if (buttonQuery.getData().equals("buttonQuantityBalls")) {
                return BotCommand.QUANTITY_BALLS;
            } else if (buttonQuery.getData().equals("buttonListByCategory")) {
                return BotCommand.LIST_BY_CATEGORY;
            } else if (buttonQuery.getData().equals("buttonWhatDiscount")) {
                return BotCommand.WHAT_DISCOUNT;
            } else if (buttonQuery.getData().equals("buttonPopularProducts")) {
                return BotCommand.POPULAR_PRODUCTS;
            } else if (buttonQuery.getData().equals("buttonCategorization")) {
                return BotCommand.CATEGORIZATION;
            } else if (buttonQuery.getData().equals("buttonDischarge")) {
                return BotCommand.GET_DISCHARGE;
            } else if (buttonQuery.getData().equals("buttonInputData")) {
                return BotCommand.INPUT_DATA;
            } else if (buttonQuery.getData().equals("buttonOperations")) {
                return BotCommand.OPERATIONS;
            } else if (buttonQuery.getData().equals("buttonLeaveFeedBack")) {
                return BotCommand.LEAVE_FEEDBACK;
            } else if (buttonQuery.getData().equals("buttonCancelToInfo")) {
                return BotCommand.CANCEL_TO_CARD_INFO;
            } else if (buttonQuery.getData().equals("buttonStatusUp")) {
                return BotCommand.STATUS_UP_CARD;
            } else if (buttonQuery.getData().equals("buttonCloseCard")) {
                return BotCommand.CLOSE_CARD;
            } else if (buttonQuery.getData().equals("buttonCancelToCatalog")) {
                return BotCommand.CANCEL_TO_CATALOG;
            } else if (buttonQuery.getData().equals("buttonAddresses")) {
                return BotCommand.SEARCH_BY_GEO;
            } else if (buttonQuery.getData().equals("buttonInviteFriend")) {
                return BotCommand.INVITE_FRIEND;
            } else if (buttonQuery.getData().equals("buttonCancelToCategoryProd")) {
                return BotCommand.CANCEL_TO_CATEGORY_PRODUCT;
            } else if (buttonQuery.getData().equals("buttonBuyCard")) {
                return BotCommand.BUY_CARD;
            }
        }
        return BotCommand.NONE;
    }
}