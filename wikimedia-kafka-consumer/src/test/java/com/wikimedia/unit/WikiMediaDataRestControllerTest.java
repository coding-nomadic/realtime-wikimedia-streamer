package com.wikimedia.unit;

import com.wikimedia.unit.controller.WikiMediaDataRestController;
import com.wikimedia.unit.entities.WikiMediaData;
import com.wikimedia.unit.repository.WikiMediaDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class WikiMediaDataRestControllerTest {

    @Mock
    private WikiMediaDataRepository repository;

    @InjectMocks
    private WikiMediaDataRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMediaDataBySize() {
        // Arrange
        int page = 0;
        int size = 30;
        Pageable pageable = PageRequest.of(page, size);
        Page<WikiMediaData> mockPage = mock(Page.class);
        when(repository.findAll(pageable)).thenReturn(mockPage);
        // Act
        ResponseEntity<Page<WikiMediaData>> response = controller.getAllMediaDataBySize(page, size);
        // Assert
        assertNotNull(response);
    }

    @Test
    void testGetMediaDataById_ExistingId() {
        // Arrange
        String existingId = "1";
        WikiMediaData mockEntity = mock(WikiMediaData.class);
        when(repository.findById(existingId)).thenReturn(Optional.of(mockEntity));
        // Act
        ResponseEntity<WikiMediaData> response = controller.getMediaDataById(existingId);
        // Assert
        assertEquals(mockEntity, response.getBody());
    }

    @Test
    void testGetMediaDataById_NonexistentId() {
        // Arrange
        String nonexistentId = "100";
        when(repository.findById(nonexistentId)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<WikiMediaData> response = controller.getMediaDataById(nonexistentId);

        // Assert
        assertEquals(ResponseEntity.notFound().build(), response);
    }
}
