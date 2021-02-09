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


    public static Responses recipientList(){
        return Responses.builder()
                .status(2)
                .message("История получателя!")
                .build();
    }

    public static Responses userDoesntExist(){
        return Responses.builder()
                .status(3)
                .message("Пользователь не найден")
                .build();
    }

    public static Responses senderSubsId(){
        return Responses.builder()
                .status(4)
                .message("Успешно! SubsID отправителя")
                .build();
    }

    public static Responses cancelled(){
        return Responses.builder()
                .status(5)
                .message("STATUS CANCELLED!!")
                .build();
    }

    public static Responses delete(){
        return Responses.builder()
                .status(6)
                .message("Успешно удалено")
                .build();
    }

    public static Responses listEmpty(){
        return Responses.builder()
                .status(7)
                .message("Список пуст")
                .build();
    }

    public static Responses linkSendSuccsess(){
        return Responses.builder()
                .status(8)
                .message("Ссылка успешно отправлена!")
                .build();
    }
    public static Responses thankYou(){
        return Responses.builder()
                .status(9)
                .message("Благодарим за пользование нашим операторм связи!")
                .build();
    }



}
