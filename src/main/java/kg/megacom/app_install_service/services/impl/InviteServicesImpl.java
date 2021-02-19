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
    Map<String, Object> map = new HashMap<>();
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Responses inviteUser(Long senderSubsId, Long recipientSubsId) {
        responses = Responses.linkSendSuccsess();
        List<StatusEvents> recipientHistories = eventRepository.findByRecipientSubsId(recipientSubsId);

        if (recipientHistories.size() == 1) {
            StatusEvents statusCancelled = recipientHistories.get(0);
            if (statusCancelled.getStatus().equals(Status.SUCCSESS)) {
                return responses = Responses.userIsBusy();
            }
            statusCancelled.setEventDate(new Date());
            statusCancelled.setStatus(Status.CANCELLED);
            eventRepository.save(statusCancelled);
        }


        if (recipientHistories.size() > 1) {
            StatusEvents statusCancelled = recipientHistories.get(recipientHistories.size() - 1);
            if (statusCancelled.getStatus().equals(Status.SUCCSESS)) {
                return responses = Responses.userIsBusy();
            }
            statusCancelled.setEventDate(new Date());
            statusCancelled.setStatus(Status.CANCELLED);
            eventRepository.save(statusCancelled);
        }


        saveRecipient(senderSubsId, recipientSubsId);

        return responses;
    }

    @Override
    public Responses getSenderSubsId(Long recipientSubsId) {
        List<StatusEvents> recipientHistories = eventRepository.findByRecipientSubsId(recipientSubsId);
        if (recipientHistories.size() == 0) {
            responses = Responses.userDoesntExist();
            return responses;
        }
        if (recipientHistories.size() >= 1) {
            StatusEvents statusEvents = recipientHistories.get(recipientHistories.size() - 1);
            Long senderSubsId = statusEvents.getSenderSubsId();
            statusEvents.setEventDate(new Date());
            statusEvents.setStatus(Status.SUCCSESS);
            eventRepository.save(statusEvents);
            responses = Responses.senderSubsId();
            responses.setObject(senderSubsId);
        }
        return responses;
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

        if (lastRecipient == null) {
           return saveRecipient(senderSubsId,recipientSubsId);
        }
        if (lastRecipient.getStatus().equals(Status.SUCCSESS)) {

            map.put("message","The recipient has already downloaded the application!");
            return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        }
        if ((lastRecipient.getStatus().equals(Status.OPEN))) {
            if (lastRecipient.getSenderSubsId().equals(senderSubsId)) {
                map.put("message","The sender has already sent the link to the current recipient");
                return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
            }
            lastRecipient.setEventDate(new Date());
            lastRecipient.setStatus(Status.CANCELLED);
            eventRepository.save(lastRecipient);
            return saveRecipient(senderSubsId, recipientSubsId);
        }

        map.put("message", "unexpected error!");
        return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);


    }

    @Override
    public Object userInvite(Long senderSubsId, Long recipientSubsId) {
        StatusEvents lastRecipient = eventRepository.getLastEvent(recipientSubsId);
        if (lastRecipient == null) {

            return saveRecipient(senderSubsId, recipientSubsId);
        }
        if ((lastRecipient.getStatus().equals(Status.OPEN))) {
            if (lastRecipient.getSenderSubsId() == senderSubsId) {
                return "exist";
            }
            lastRecipient.setEventDate(new Date());
            lastRecipient.setStatus(Status.CANCELLED);
            eventRepository.save(lastRecipient);
            return saveRecipient(senderSubsId, recipientSubsId);
        }
        return null;
    }


    private ResponseEntity<?> saveRecipient(Long senderSubsId, Long recipientSubsId) {


        StatusEvents newRecipient = new StatusEvents();
        newRecipient.setEventDate(new Date());
        newRecipient.setRecipientSubsId(recipientSubsId);
        newRecipient.setSenderSubsId(senderSubsId);
        newRecipient.setStatus(Status.OPEN);
        eventRepository.save(newRecipient);
        map.put("message", "Успешно");
        map.put("result", newRecipient);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
