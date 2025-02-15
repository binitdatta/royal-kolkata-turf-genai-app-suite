package com.rollingstone.services;

import com.rollingstone.entity.ClubMember;
import com.rollingstone.repository.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubMemberService {

    @Autowired
    private ClubMemberRepository repository;

    public List<ClubMember> getAllMembers() {
        return repository.findAll();
    }

    public Optional<ClubMember> getMemberById(Integer id) {
        return repository.findById(id);
    }

    public ClubMember createMember(ClubMember member) {
        return repository.save(member);
    }

    public ClubMember updateMember(Integer id, ClubMember member) {
        if (repository.existsById(id)) {
            member.setMemberId(id);
            return repository.save(member);
        }
        throw new RuntimeException("Member not found with id: " + id);
    }

    public void deleteMember(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Member not found with id: " + id);
        }
    }
}
