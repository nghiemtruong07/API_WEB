package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.PositionDTO;
import com.vti.entity.Position;
import com.vti.service.IPositionService;

@RestController
@RequestMapping(value = "api/v1/positions")
@CrossOrigin("*")
public class PositionController {
	@Autowired
	private IPositionService positionService;
	
	@GetMapping
	public ResponseEntity<?> getAllPosition()
	{
		List<Position> listPositions = positionService.getAllPosition();
		
		List<PositionDTO> listPositionDTOs = new ArrayList<>();
		for (Position position : listPositions) {
			PositionDTO positionDTO = new PositionDTO();
			positionDTO.setId(position.getId());
			positionDTO.setName(position.getName().toString());
			
			listPositionDTOs.add(positionDTO);
		}
		
		return new ResponseEntity<>(listPositionDTOs,HttpStatus.OK);
		
	}
}
