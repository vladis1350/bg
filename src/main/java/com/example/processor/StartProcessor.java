package com.example.processor;

import com.example.service.ReplyMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartProcessor implements Processor {

    @Autowired
    ReplyMessagesService replyMessagesService;

    @Override
    public String run() {
        return "Привет! Я, Искра - виртуальный помощник в твоих покупках и экономии.\n" +
                "Чтобы начать работу и получать скидки мне нужен твой номер телефона - отправь его мне." +
                "\nПосле этого ты сможешь ознакомиться c каталогом товаров и магазинами.\n\nУдачных покупок!";
    }
}
