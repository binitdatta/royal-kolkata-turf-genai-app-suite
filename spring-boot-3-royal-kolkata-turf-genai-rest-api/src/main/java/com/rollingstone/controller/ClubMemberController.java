package com.rollingstone.controller;

import com.rollingstone.entity.ClubMember;
import com.rollingstone.services.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/club-members")
public class ClubMemberController {

    @Autowired
    private ClubMemberService service;

    @GetMapping
    public ResponseEntity<List<ClubMember>> getAllMembers() {
        return ResponseEntity.ok(service.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMember> getMemberById(@PathVariable Integer id) {
        Optional<ClubMember> member = service.getMemberById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClubMember> createMember(@RequestBody ClubMember member) {
        return ResponseEntity.ok(service.createMember(member));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubMember> updateMember(@PathVariable Integer id, @RequestBody ClubMember member) {
        try {
            return ResponseEntity.ok(service.updateMember(id, member));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        try {
            service.deleteMember(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
