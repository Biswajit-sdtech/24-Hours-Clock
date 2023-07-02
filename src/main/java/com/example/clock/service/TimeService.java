package com.example.clock.service;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

	public String convertToWords(int h, int m) {
	String words=null;
		if (h == 0 && m == 0) {
			return "Midnight";
		} else if (h == 12 && m == 0) {
			return "Midday";
		} else {
			 String nums[] = { "zero", "one", "two", "three", "four",
                     "five", "six", "seven", "eight", "nine",
                     "ten", "eleven", "twelve", "thirteen",
                     "fourteen", "fifteen", "sixteen", "seventeen",
                     "eighteen", "nineteen", "twenty", "twenty one",
                     "twenty two", "twenty three", "twenty four",
                     "twenty five", "twenty six", "twenty seven",
                     "twenty eight", "twenty nine",
                 };

 if (m == 0)
	 words = (nums[h] + " o' clock ");

 else if (m == 1)
	 words = ("one minute past " + 
                                     nums[h]);

 else if (m == 59)
	 words = ("one minute to " + 
                     nums[(h % 12) + 1]);

 else if (m == 15)
	 words = ("quarter past " + nums[h]);

 else if (m == 30)
	 words = ("half past " + nums[h]);

 else if (m == 45)
	 words = ("quarter to " + 
                         nums[(h % 12) + 1]);

 else if (m <= 30)
	 words = ( nums[m] + " minutes past " +
                                             nums[h]);

 else if (m > 30)
	 words = ( nums[60 - m] + " minutes to " +     
                                         nums[(h % 12) + 1]);
}
		
return words;
		
	}

}


