package com.app.controller;

import com.app.model.Group;
import com.app.service.GroupService;
import io.swagger.v3.oas.annotations.Parameter;
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
                                            @Parameter(description = "subject id of the group") @RequestParam Long subjectId,
                                            @Parameter(description = "user id of the group") @RequestParam Long userId) {
        groupService.createNewGroup(name, subjectId, userId);
        return ResponseEntity.ok("group was created");
    }

    @PatchMapping
    public ResponseEntity<?> setGroup(@Parameter(description = "id of the group") @RequestParam Long id,
                                      @Parameter(description = "name of the group") @RequestParam String name,
                                      @Parameter(description = "subject id of the group") @RequestParam Long subjectId,
                                      @Parameter(description = "user id of the group") @RequestParam Long userId) {
        groupService.setGroup(id, name, subjectId, userId);
        return ResponseEntity.ok("group was set");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGroup(@Parameter(description = "id of the group") @RequestParam Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok("group was deleted");
    }

    @GetMapping
    public List<Group> fetchGroups(@Parameter(description = "id of the user") @RequestParam Long id) {
        return groupService.fetchGroups(id);
    }

}
