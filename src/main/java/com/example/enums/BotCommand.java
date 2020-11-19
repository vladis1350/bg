package com.example.enums;


public enum BotCommand {
    START("/start"),
    SETTING("/settings"),
    NONE("/none"),
    MY_CARTS("Мои карты"),
    PARTICIPANT_PROGRAMS("Участники программы"),
    CATALOG("Каталог"),
    SEE_CATALOG("Смотреть каталог"),
    SHARES("Акции"),
    MY_INFORMATION("Моя информация"),
    MY_DISCOUNT("Моя скидка"),
    INFORMATION("Информация"),
    DELIVERY("Доставка"),
    HELP("Помощь"),
    CANCEL("Назад"),
    SEND_PHONE_NUMBER,
    CLOSE_CARD,
    CANCEL_TO_CATALOG,
    INVITE_FRIEND,
    SEARCH_BY_GEO,
    SEND_PAY,
    BUY_CARD,
    CANCEL_TO_CARD_INFO,
    CANCEL_TO_CATEGORY_PRODUCT,
    MAIN_MENU,
    STATUS_UP_CARD,
    INFORMATION_CART,
    LIST_BY_CATEGORY,
    WHAT_DISCOUNT,
    POPULAR_PRODUCTS,
    CATEGORIZATION,
    GET_DISCHARGE,
    INPUT_DATA,
    OPERATIONS,
    ASK_AGE,
    GET_BTN_DISCHARGE,
    LEAVE_FEEDBACK,
    FEEDBACK_FILLED,
    QUANTITY_BALLS;

    String command;

    BotCommand() {

    }

    public String getCommand() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }


}
