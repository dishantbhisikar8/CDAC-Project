package com.airlinereservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airlinereservation.dto.AirplaneResponseDto;
import com.airlinereservation.dto.CommonApiResponse;
import com.airlinereservation.entity.Airplane;
import com.airlinereservation.resource.AirplaneResource;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/airplane/")
@CrossOrigin(origins = "http://localhost:3000")
public class AirplaneController {
	
	@Autowired
	private AirplaneResource airplaneResource;
	
	@PostMapping("add")
	@ApiOperation(value = "Api to add the fetch all lAirplane")
	public ResponseEntity<CommonApiResponse> addAirplane(@RequestBody Airplane airplane) {
		return this.airplaneResource.addAirplane(airplane);
	}
	
	@GetMapping("/fetch/all")
	@ApiOperation(value = "Api to fetch all airplane")
	public ResponseEntity<AirplaneResponseDto> fetchAllAirplane() {
		return this.airplaneResource.fetchAllAirplane();
	}

}
