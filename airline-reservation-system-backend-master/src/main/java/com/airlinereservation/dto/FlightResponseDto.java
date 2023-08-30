package com.airlinereservation.dto;

import java.util.ArrayList;
import java.util.List;

import com.airlinereservation.entity.Flight;

import lombok.Data;

@Data
public class FlightResponseDto extends CommonApiResponse {
	
	private List<Flight> flights = new ArrayList<>();

}
