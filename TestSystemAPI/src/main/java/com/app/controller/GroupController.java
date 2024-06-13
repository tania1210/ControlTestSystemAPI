package com.app.controller;

import com.app.model.Group;
import com.app.service.GroupService;
import exceptions.GroupAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/groups")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createNewGroup(@Parameter(description = "Name of the group") @RequestParam String name,
                                            @Parameter(description = "Subject id of the group") @RequestParam Long subjectId,
                                            @Parameter(description = "User id of the group") @RequestParam Long userId) {
        try {
            Group groupDTO = groupService.createNewGroup(name, subjectId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupDTO);
        } catch (GroupAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> setGroup(@Parameter(description = "id of the group") @RequestParam Long id,
                                      @Parameter(description = "name of the group") @RequestParam String name,
                                      @Parameter(description = "subject id of the group") @RequestParam Long subjectId,
                                      @Parameter(description = "user id of the group") @RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(groupService.setGroup(id, name, subjectId, userId));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteGroup(@Parameter(description = "id of the group") @RequestParam Long id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.ok("group was deleted");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<?> fetchGroups(@Parameter(description = "id of the user") @RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(groupService.fetchGroups(id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
