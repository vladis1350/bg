package com.example.buttonHandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyInfoButtonHandler {

    public SendMessage getButtonMyCartsAndMessage(long chatId) {
        return new SendMessage(chatId, "Выберите действие").setReplyMarkup(getButtonMyCarts());
    }

    public SendMessage getButtonBuyCardAndMessage(long chatId) {
        return new SendMessage(chatId, "Купите нашу карту").setReplyMarkup(getButtonBuy());
    }

    public EditMessageText getButtonCancelAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonCancelToInfo());
    }

    private InlineKeyboardMarkup getButtonCancelToInfo() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCancelToInfo = new InlineKeyboardButton().setText("Назад");

        buttonCancelToInfo.setCallbackData("buttonCancelToInfo");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonCancelToInfo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup getButtonBuy() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonBuyCard = new InlineKeyboardButton().setText("Купить");

        buttonBuyCard.setCallbackData("buttonBuyCard");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonBuyCard);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    public EditMessageText getButtonCancelToInfoAndMessage(long chatId, int messageId, String text) {
        return new EditMessageText()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(getButtonMyCarts());
    }

    private InlineKeyboardMarkup getButtonMyCarts() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonMyCarts = new InlineKeyboardButton().setText("Моя карта");
        InlineKeyboardButton buttonQuantityBalls = new InlineKeyboardButton().setText("Мои баллы");
        InlineKeyboardButton buttonStatusUp = new InlineKeyboardButton().setText("Повысить статус");
        InlineKeyboardButton buttonCloseCard = new InlineKeyboardButton().setText("Отозвать карту");

        buttonCloseCard.setCallbackData("buttonCloseCard");
        buttonStatusUp.setCallbackData("buttonStatusUp");
        buttonMyCarts.setCallbackData("buttonMyCarts");
        buttonQuantityBalls.setCallbackData("buttonQuantityBalls");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonMyCarts);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonQuantityBalls);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonStatusUp);
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(buttonCloseCard);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
