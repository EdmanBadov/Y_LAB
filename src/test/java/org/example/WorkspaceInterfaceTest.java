package org.example;

import org.example.in.WorkspaceInterface;
import org.example.out.WorkspaceActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WorkspaceInterfaceTest {

    private WorkspaceActions workspaceActions;
    private WorkspaceInterface workspaceInterface;

    @BeforeEach
    public void setUp() {
        workspaceActions = mock(WorkspaceActions.class);
        workspaceInterface = new WorkspaceInterface(workspaceActions);
    }

    @Test
    public void testCreateWorkspace() {
        workspaceInterface.createWorkspace("Новое рабочее место", "1 тип");
        verify(workspaceActions).createWorkspace("Новое рабочее место", "1 тип");
    }

    @Test
    public void testUpdateWorkspaceName() {
        workspaceInterface.updateWorkspaceName(1, "имя");
        verify(workspaceActions).updateWorkspaceName(1, "имя");
    }

    @Test
    public void testUpdateWorkspaceType() {
        workspaceInterface.updateWorkspaceType(1, "2 тип");
        verify(workspaceActions).updateWorkspaceType(1, "2 тип");
    }

    @Test
    public void testViewAllWorkspaces() {
        workspaceInterface.viewAllWorkspaces();
        verify(workspaceActions).viewAllWorkspaces();
    }

    @Test
    public void testViewWorkspace() {
        workspaceInterface.viewWorkspace(1);
        verify(workspaceActions).viewWorkspaceById(1);
    }

    @Test
    public void testDeleteWorkspace() {
        workspaceInterface.deleteWorkspace(1);
        verify(workspaceActions).deleteWorkspace(1);
    }

    @Test
    public void testChooseType() {
        String type1 = workspaceInterface.chooseType(1);
        assertEquals("1 тип", type1);

        String type2 = workspaceInterface.chooseType(2);
        assertEquals("2 тип", type2);

        String type3 = workspaceInterface.chooseType(3);
        assertEquals("3 тип", type3);
    }
}
