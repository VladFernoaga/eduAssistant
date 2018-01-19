package ro.unitbv.eduassistant.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.fouad.jtb.core.exceptions.NegativeResponseException;
import ro.unitbv.eduassistant.chatbot.handler.SenderService;

@RestController
public class TriggerQuestionController {
	
	private SenderService senderService;

	@Autowired
	public TriggerQuestionController(SenderService senderService) {
		this.senderService = senderService;
	}

	  @RequestMapping(value = "/question/{lessonSessionKey}/{questionId}",
		      method = RequestMethod.POST)
	public ResponseEntity<Void> triggerQuestion(@PathVariable("lessonSessionKey") String lessonSessionKey, @PathVariable("questionId") String questionId){
		  try {
			senderService.sendQuestionToRegisteredStudents(lessonSessionKey,questionId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NegativeResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		return ResponseEntity.ok().build();
	}
	  
}
