package com.app.service;

import com.app.model.Group;
import com.app.model.User;
import com.app.repository.GroupRepository;
import com.app.repository.SubjectRepository;
import com.app.repository.UserRepository;
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

    public void createNewGroup(String name, Long subjectId, Long userId) {
        groupRepository.save(new Group(name, subjectRepository.getById(subjectId), userRepository.getById(userId)));

    }

    public void setGroup(Long id, String name, Long subjectId, Long userId) {
        if(id == null) {
            throw new NullPointerException();
        }else {
            Group group = groupRepository.getById(id);
            if(name != null) {
                group.setName(name);
            }if(subjectId != null) {
                group.setSubjectId(subjectRepository.getById(subjectId));
            }if(userId != null) {
                group.setUserId(userRepository.findById(userId).get());
            }else {

            }
            groupRepository.save(group);
        }
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> fetchGroups(Long id) {
        return groupRepository.findAllGroupByUserId(id);
    }



}
