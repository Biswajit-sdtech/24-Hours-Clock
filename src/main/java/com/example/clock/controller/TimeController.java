package com.example.clock.controller;

import java.time.LocalDateTime;
import com.example.clock.service.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimeController {

	private final TimeService timeService;

	public TimeController(TimeService timeService) {
		this.timeService = timeService;
	}
	
    /* In the problem it is specified for current time,it is a single method */
	
	@GetMapping("/convertCurrentTime")
	public ResponseEntity<String> convertCurrentTime() {
		try {
			LocalDateTime currentTime = LocalDateTime.now();
			String timeInWords = timeService.convertToWords(currentTime.getHour(), currentTime.getMinute());
			return ResponseEntity.ok("It's " + timeInWords);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
		}
	}
	
    /*Started below method for custom input of hour and minute,User can specify his/her input in this method,Verified with Postman*/
	
	@GetMapping("/convertInputTime/{hour}/{minute}")
	public ResponseEntity<String> convertInputTime(@PathVariable String hour, @PathVariable String minute) {
		try {
			String timeInWords = timeService.convertToWords(Integer.parseInt(hour), Integer.parseInt(minute));
			return ResponseEntity.ok("It's " + timeInWords);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
		}
	}

}
