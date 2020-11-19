package com.example.buttonHandler;

import com.example.service.LocaleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Component
public class ButtonHandler {

    @Autowired
    private LocaleMessageService localeMessageService;

    public SendMessage getButtonCatalogAndMessage(long chatId) {
        return new SendMessage(chatId, "Выберите действие").setReplyMarkup(getButtonParticipantPrograms());
    }

    public EditMessageText getButtonCancelToParticipantAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonCancelToCatalog());
    }

    public EditMessageText getCancelToParticipantAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonParticipantPrograms());
    }

    public EditMessageText getButtonCancelToCategoryProdAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonCancelToCategoryProd());
    }

    public EditMessageText getCancelToCategoryProdAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonCatalog());
    }

    public SendMessage getButtonParticipantProgramsAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonParticipantPrograms());
    }

    public SendMessage getButtonCatalogAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonCatalog());
    }

    public EditMessageText getButtonPopularProductsAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonParticipantPrograms());
    }

    public SendMessage getButtonMyInformationAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text)
                .setReplyMarkup(getButtonMyInformation());
    }

    public SendMessage getButtonDischargeAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonDischarge());
    }

    public SendMessage getButtonDeliveryInfoAndMessage(long chatId, String text) {
        return new SendMessage(chatId, text).setReplyMarkup(getButtonDeliveryInfo());
    }

    private InlineKeyboardMarkup getButtonCancelToCatalog() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCancelToCatalog = new InlineKeyboardButton().setText("Назад");

        buttonCancelToCatalog.setCallbackData("buttonCancelToCatalog");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonCancelToCatalog);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonCancelToCategoryProd() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCancelToCategoryProd = new InlineKeyboardButton().setText("Назад");

        buttonCancelToCategoryProd.setCallbackData("buttonCancelToCategoryProd");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonCancelToCategoryProd);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonDeliveryInfo() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonLeaveFeedBack = new InlineKeyboardButton().setText("Оставить отзыв");

        buttonLeaveFeedBack.setCallbackData("buttonLeaveFeedBack");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonLeaveFeedBack);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonParticipantPrograms() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonListByCategory = new InlineKeyboardButton().setText("Список по категориям");
        InlineKeyboardButton buttonAddresses = new InlineKeyboardButton().setText("Адреса(поиск по геопозиции)");
        InlineKeyboardButton buttonInviteFriend = new InlineKeyboardButton().setText("Пригласить друга");

        buttonInviteFriend.setCallbackData("buttonInviteFriend");
        buttonAddresses.setCallbackData("buttonAddresses");
        buttonListByCategory.setCallbackData("buttonListByCategory");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonListByCategory);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonAddresses);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonInviteFriend);


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonCatalog() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonPopularProducts = new InlineKeyboardButton().setText("Популярные товары");
        InlineKeyboardButton buttonCategorization = new InlineKeyboardButton().setText("Категории товаров");
        InlineKeyboardButton buttonSearchPopularProduct = new InlineKeyboardButton().setText("Поиск");

        buttonSearchPopularProduct.setCallbackData("buttonSearchPopularProduct");
        buttonPopularProducts.setCallbackData("buttonPopularProducts");
        buttonCategorization.setCallbackData("buttonCategorization");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonPopularProducts);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonCategorization);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonSearchPopularProduct);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonMyInformation() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonInputData = new InlineKeyboardButton().setText("Изменить профиль");
        InlineKeyboardButton buttonOperations = new InlineKeyboardButton().setText("Мои операции");

        buttonInputData.setCallbackData("buttonInputData");
        buttonOperations.setCallbackData("buttonOperations");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonInputData);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonOperations);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonDischarge() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonDischarge = new InlineKeyboardButton().setText("Получить выписку");

        buttonDischarge.setCallbackData("buttonDischarge");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonDischarge);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
