package xyz.jiyong.wantedpreonboarding.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.jiyong.wantedpreonboarding.global.response.CustomResponse;
import xyz.jiyong.wantedpreonboarding.recruitment.dto.RecruitmentDto;
import xyz.jiyong.wantedpreonboarding.recruitment.service.RecruitmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping("")
    public ResponseEntity<List<RecruitmentDto>> getRecruitments(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return CustomResponse.of(
                HttpStatus.OK, recruitmentService.getRecruitments(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentDto> getRecruitment(
            @PathVariable long id) {
        return CustomResponse.of(
                HttpStatus.OK, recruitmentService.getRecruitment(id));
    }

    @PostMapping("")
    public ResponseEntity<RecruitmentDto> postRecruitment(
            @RequestBody RecruitmentDto recruitmentDto) {
        return CustomResponse.of(
                HttpStatus.CREATED, recruitmentService.createRecruitment(recruitmentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecruitmentDto> patchRecruitment(
            @RequestBody RecruitmentDto recruitmentDto,
            @PathVariable long id
    ) {
        return CustomResponse.of(
                HttpStatus.OK, recruitmentService.modifyRecruitment(recruitmentDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitment(@PathVariable long id) {
        recruitmentService.destroyRecruitment(id);
        return CustomResponse.ofNoContent(HttpStatus.OK);
    }
}
