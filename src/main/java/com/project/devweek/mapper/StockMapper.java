package com.project.devweek.mapper;

import com.project.devweek.model.Stock;
import com.project.devweek.model.dto.stockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {


    public Stock toEntity(stockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());
        return stock;
    }

    public stockDTO toDto(Stock stock) {
        stockDTO dto = new stockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());
        return dto;
    }

    public List<stockDTO> toDto(List<Stock> listStock) {
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
    }
}
