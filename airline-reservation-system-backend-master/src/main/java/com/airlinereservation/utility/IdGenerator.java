package com.airlinereservation.utility;

import java.util.UUID;

public class IdGenerator {
	
	public static String generateBookingId() {
		UUID uuid = UUID.randomUUID();
        String uuidHex = uuid.toString().replace("-", ""); // Remove hyphens
        String uuid16Digits = uuidHex.substring(0, 16); // Take the first 16 characters
        
        return uuid16Digits;
    }
	
	public static String generateFlightNumber() {
		UUID uuid = UUID.randomUUID();
        String uuidHex = uuid.toString().replace("-", ""); // Remove hyphens
        String uuid5Digits = uuidHex.substring(0, 5); // Take the first 16 characters
        
        return uuid5Digits;
    }

}
