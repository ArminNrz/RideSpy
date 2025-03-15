package com.example.ridespy.infra.ports.api;

import com.example.ridespy.application.BikerLocationApp;
import com.example.ridespy.infra.ports.api.dto.BikerNearByDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/near-by")
@RequiredArgsConstructor
public class NearbyController {

    private final BikerLocationApp app;

    @GetMapping
    public ResponseEntity<List<BikerNearByDto>> getNearByBiker(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon
    ) {
        return ResponseEntity.ok(app.findNearBy(lat, lon));
    }
}
