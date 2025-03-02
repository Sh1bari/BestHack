package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.exceptions.GeneralException;

import java.io.File;

import static ru.noxly.efs.common.CsvGenerator.generateCsvFile;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Admin API", description = "")
public class AdminController {

    @Operation(summary = "Get test csv file")
    @ApiResponses()
    @GetMapping("/get-test-csv")
    public ResponseEntity<Resource> downloadCsv(@RequestParam Integer from, @RequestParam Integer to) {
        File file = generateCsvFile(from, to);
        if (file == null || !file.exists()) {
            throw new GeneralException(500, "Error while generating CSV file");
        }

        Resource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
