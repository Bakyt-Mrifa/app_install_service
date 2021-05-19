package kg.megacom.app_install_service.services.impl;

import kg.megacom.app_install_service.dao.EventRepository;
import kg.megacom.app_install_service.models.entity.StatusEvents;
import kg.megacom.app_install_service.models.enums.Status;
import kg.megacom.app_install_service.models.responses.Responses;
import kg.megacom.app_install_service.services.InviteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InviteServicesImpl implements InviteServices {
    private Responses responses;
    Map<String, String> map = new HashMap<>();
    Map<String, Object> mapObject = new HashMap<>();
    @Autowired
    private EventRepository eventRepository;

    @Override
    public ResponseEntity<?> getSenderSubsId(Long recipientSubsId) {
        StatusEvents lastRecipient = eventRepository.getLastEvent(recipientSubsId);
        if (lastRecipient == null) {
            map.put("error","user not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        if (lastRecipient.getStatus().equals(Status.SUCCSESS)) {
            map.put("error","The recipient has already downloaded the application!");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }
            Long senderSubsId = lastRecipient.getSenderSubsId();
            lastRecipient.setEventDate(new Date());
            lastRecipient.setStatus(Status.SUCCSESS);
            eventRepository.save(lastRecipient);
            mapObject.put("success", senderSubsId);
            return new ResponseEntity<>(mapObject, HttpStatus.OK);
    }

    @Override
    public Responses getRecipientHistory(Long recipientSubsId) {
        List<StatusEvents> recipientHistories = eventRepository.findByRecipientSubsId(recipientSubsId);
        if (recipientHistories.size() == 0) {
            responses = Responses.listEmpty();
            return responses;
        }
        if (recipientHistories.size() >= 1) {
            responses = Responses.recipientList();
            responses.setObject(recipientHistories);
        }
        return responses;
    }

    @Override
    public ResponseEntity<?> invite(Long senderSubsId, Long recipientSubsId) {
        StatusEvents lastRecipient = eventRepository.getLastEvent(recipientSubsId);

//create and save new recipient
        if (lastRecipient == null) {
            mapObject.put("success", saveRecipient(senderSubsId,recipientSubsId));
            return new ResponseEntity<>(mapObject, HttpStatus.ACCEPTED);
        }

//verification - has the recipient already download the app?
        if (lastRecipient.getStatus().equals(Status.SUCCSESS)) {
             map.put("error","The recipient has already downloaded the application!");
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }

//change status "OPEN" to "CANCELLED"
       if (lastRecipient.getStatus().equals(Status.OPEN)) {
           System.out.println(lastRecipient.getSenderSubsId() +": from DB - sender: "+senderSubsId);

//verification - has the sender already sent the link?
            if (lastRecipient.getSenderSubsId().equals(senderSubsId)) {
                map.put("error","The sender has already sent the link to the current recipient");
                return new ResponseEntity<>(map, HttpStatus.ALREADY_REPORTED);
            }
            lastRecipient.setEventDate(new Date());
            lastRecipient.setStatus(Status.CANCELLED);
            eventRepository.save(lastRecipient);
            mapObject.put("success", saveRecipient(senderSubsId,recipientSubsId));
            return new ResponseEntity<>(mapObject, HttpStatus.ACCEPTED);
        }

        map.put("error", "unexpected error! + + +");
        return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    private StatusEvents saveRecipient(Long senderSubsId, Long recipientSubsId) {
        StatusEvents newRecipient = new StatusEvents();
        newRecipient.setEventDate(new Date());
        newRecipient.setRecipientSubsId(recipientSubsId);
        newRecipient.setSenderSubsId(senderSubsId);
        newRecipient.setStatus(Status.OPEN);
        eventRepository.save(newRecipient);
        return newRecipient;
    }
}
