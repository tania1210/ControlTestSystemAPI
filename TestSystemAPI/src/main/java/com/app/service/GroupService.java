package com.app.service;

import com.app.model.Group;
import com.app.model.User;
import com.app.repository.GroupRepository;
import com.app.repository.SubjectRepository;
import com.app.repository.UserRepository;
import exceptions.EmailNotFoundException;
import exceptions.GroupAlreadyExistsException;
import exceptions.SubjectAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public Group createNewGroup(String name, Long subjectId, Long userId) throws EntityNotFoundException, GroupAlreadyExistsException {
        if(groupRepository.findByName(name).isPresent()) {
            System.out.println("throws goup is present");
            throw new GroupAlreadyExistsException("group with this name is already exists");
        }else {
            if(subjectRepository.findById(subjectId).isEmpty()) {
                System.out.println("throws subject not found");
                throw new EntityNotFoundException("subject's not found. Wrong id");
            }else {
                if(userRepository.findById(userId).isEmpty()) {
                    System.out.println("throws user not found");
                    throw new EntityNotFoundException("user's not found. Wrong id");
                }else {
                    Group group = groupRepository.save(new Group(name, subjectRepository.getById(subjectId), userRepository.getById(userId)));
                    System.out.println("group was created" + group);
                    return group;
                }
            }

        }
    }

    public Group setGroup(Long id, String name, Long subjectId, Long userId) throws EntityNotFoundException{
        if(groupRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("group's not found. Wrong id");
        }else {
            if(subjectRepository.findById(subjectId).isEmpty()) {
                throw new EntityNotFoundException("subject's not found. Wrong id");
            }else {
                if(userRepository.findById(userId).isEmpty()) {
                    throw new EntityNotFoundException("user's not found. Wrong id");
                }else {
                    Group group = groupRepository.getById(id);
                    if(!name.equals(group.getName())) {
                        group.setName(name);
                    }if(!subjectRepository.findById(subjectId).get().equals(group.getSubjectId())) {
                        group.setSubjectId(subjectRepository.getById(subjectId));
                    }if(!userRepository.findById(userId).get().equals(group.getUserId())) {
                        group.setUserId(userRepository.getById(userId));
                    }
                    return groupRepository.save(group);
                }
            }
        }
    }

    public void deleteGroup(Long id) throws EntityNotFoundException {
        if(groupRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("group with this id hasn't found");
        }else {
            groupRepository.deleteById(id);
        }
    }

    public List<Group> fetchGroups(Long id) throws EntityNotFoundException {
        if(userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("user with this id hasn't found");
        }else {
            return groupRepository.findAllGroupByUserId(id);
        }

    }



}
