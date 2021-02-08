package kg.megacom.app_install_service.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Responses {
    private int status;
    private String message;
    private Object object;

    public static Responses success(){
        return Responses.builder()
                .status(1)
                .message("Операция выполнено успешно")
                .build();
    }


    public static Responses error(){
        return Responses.builder()
                .status(2)
                .message("Ошибка сохраниения в базу данных!!!")
                .build();
    }

    public static Responses exist(){
        return Responses.builder()
                .status(3)
                .message("Объект не существует!")
                .build();
    }

    public static Responses save(){
        return Responses.builder()
                .status(4)
                .message("Успешно сохранено!")
                .build();
    }

    public static Responses update(){
        return Responses.builder()
                .status(5)
                .message("Успешно обновлено!")
                .build();
    }

    public static Responses delete(){
        return Responses.builder()
                .status(6)
                .message("Успешно удалено")
                .build();
    }

    public static Responses empty(){
        return Responses.builder()
                .status(7)
                .message("Список пуст")
                .build();
    }

    public static Responses operationSuccess(){
        return Responses.builder()
                .status(8)
                .message("Операция проведена успешно")
                .build();
    }
    public static Responses thankYou(){
        return Responses.builder()
                .status(9)
                .message("Спасибо за покупку")
                .build();
    }



}
