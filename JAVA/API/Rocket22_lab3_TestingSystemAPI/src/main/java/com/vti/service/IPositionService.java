package com.vti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti.entity.Position;
@Service
public interface IPositionService {
	public List<Position> getAllPosition();
}
