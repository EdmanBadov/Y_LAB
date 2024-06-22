package org.example;

import org.example.models.Workspace;
import org.example.out.WorkspaceActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkspaceActionsTest {

    private WorkspaceActions workspaceActions;

    @BeforeEach
    public void setUp() {
        workspaceActions = new WorkspaceActions();
    }

    @Test
    public void testCreateWorkspace() {
        String result = workspaceActions.createWorkspace("Test Workspace", "Type A");
        assertEquals(WorkspaceActions.WORKSPACE_CREATED, result);
    }

    @Test
    public void testViewWorkspaceById() {
        workspaceActions.createWorkspace("Test Workspace", "тип 1");
        String result = workspaceActions.viewWorkspaceById(0);
        assertTrue(result.contains("Test Workspace"));
    }

    @Test
    public void testViewWorkspaceByIdNotFound() {
        String result = workspaceActions.viewWorkspaceById(999);
        assertEquals(WorkspaceActions.WORKSPACE_NOT_FOUND, result);
    }

    @Test
    public void testViewAllWorkspaces() {
        workspaceActions.createWorkspace("Workspace1", "тип1");
        workspaceActions.createWorkspace("Workspace2", "тип2");
        String result = workspaceActions.viewAllWorkspaces();
        assertTrue(result.contains("Workspace1"));
        assertTrue(result.contains("Workspace2"));
    }

    @Test
    public void testUpdateWorkspaceName() {
        workspaceActions.createWorkspace("Test Workspace", "тип1");
        String result = workspaceActions.updateWorkspaceName(0, "Updated Workspace");
        assertEquals(WorkspaceActions.NAME_UPDATED + "Updated Workspace", result);
    }

    @Test
    public void testUpdateWorkspaceNameNotFound() {
        String result = workspaceActions.updateWorkspaceName(999, "Updated Workspace");
        assertEquals(WorkspaceActions.WORKSPACE_NOT_FOUND, result);
    }

    @Test
    public void testUpdateWorkspaceType() {
        workspaceActions.createWorkspace("Test Workspace", "тип1");
        String result = workspaceActions.updateWorkspaceType(0, "Updated Type");
        assertEquals(WorkspaceActions.TYPE_UPDATED + "Updated Type", result);
    }

    @Test
    public void testUpdateWorkspaceTypeNotFound() {
        String result = workspaceActions.updateWorkspaceType(999, "Updated Type");
        assertEquals(WorkspaceActions.WORKSPACE_NOT_FOUND, result);
    }

    @Test
    public void testDeleteWorkspace() {
        workspaceActions.createWorkspace("Test Workspace", "Type A");
        String result = workspaceActions.deleteWorkspace(0);
        assertEquals(WorkspaceActions.WORKSPACE_REMOVED, result);
    }

    @Test
    public void testDeleteWorkspaceNotFound() {
        String result = workspaceActions.deleteWorkspace(999);
        assertEquals(WorkspaceActions.WORKSPACE_NOT_FOUND, result);
    }

    @Test
    public void testGetWorkspaceById() {
        workspaceActions.createWorkspace("Test Workspace", "Type A");
        Workspace workspace = workspaceActions.getWorkspaceById(0);
        assertNotNull(workspace);
        assertEquals("Test Workspace", workspace.getWorkspaceName());
    }

    @Test
    public void testGetWorkspaceByIdNotFound() {
        Workspace workspace = workspaceActions.getWorkspaceById(999);
        assertNull(workspace);
    }
}
