package com.faillog.failure.api;

import com.faillog.common.response.SuccessCode;
import com.faillog.failure.api.dto.request.FailureSaveRequestDto;
import com.faillog.failure.api.dto.request.FailureUpdateRequestDto;
import com.faillog.failure.api.dto.response.FailureInfoResponseDto;
import com.faillog.failure.api.dto.response.FailureListResponseDto;
import com.faillog.failure.application.FailureService;
import com.faillog.failure.domain.FailureCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.faillog.failure.common.template.ApiResTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/failures")
@Tag(name = "FAILURE API", description = "실패담 관리 api")
public class FailureController {
    private final FailureService failureService;

    @PostMapping
    @Operation(summary = "실패담 저장",  description = "실패담 저장")
    public ApiResTemplate<Void> failureSave(@RequestBody @Valid FailureSaveRequestDto failureSaveRequestDto) {
        failureService.failureSave(failureSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.FAILURE_SAVE_SUCCESS);
    }

    @GetMapping("/{failureId}")
    @Operation(summary = "실패담 상세 조회", description = "failureId를 이용하여 실패담을 상세 조회합니다.")
    public ApiResTemplate<FailureInfoResponseDto> failureFindById(@PathVariable("failureId") Long failureId) {
        FailureInfoResponseDto failure = failureService.failureFindById(failureId);

        return ApiResTemplate.success(
                SuccessCode.FAILURE_FIND_SUCCESS,
                failure
        );
    }

    @GetMapping
    @Operation(summary = "실패담 목록 조회", description = "실패담 목록을 조회합니다.")
    public ApiResTemplate<FailureListResponseDto> failureFindAll() {
        FailureListResponseDto failures = failureService.failureFindAll();
        return ApiResTemplate.success(
                SuccessCode.FAILURE_FIND_SUCCESS,
                failures
        );
    }

    @GetMapping("/search")
    @Operation(summary = "실패담 검색", description = "키워드로 실패담을 검색합니다.")
    public ApiResTemplate<FailureListResponseDto> failureSearch(
            @RequestParam String keyword
    ) {
        FailureListResponseDto failures = failureService.failureSearch(keyword);
        return ApiResTemplate.success(
                SuccessCode.FAILURE_SEARCH_SUCCESS,
                failures
        );
    }

    @GetMapping("/categories/{category}")
    @Operation(summary = "카테고리별 실패담 조회", description = "카테고리별 실패담을 조회합니다.")
    public ApiResTemplate<FailureListResponseDto> failureFindByCategory(
            @PathVariable("category")FailureCategory category) {
        FailureListResponseDto failures = failureService.failureFindByCategory(category);
        return ApiResTemplate.success(
                SuccessCode.FAILURE_FIND_BY_CATEGORY_SCCESS,
                failures
        );
    }

    @GetMapping("/popular")
    @Operation(summary = "인기 실패담 조회", description = "조회수가 높은 실패담을 조회합니다.")
    public ApiResTemplate<FailureListResponseDto> failureFindPopular() {
        FailureListResponseDto failures = failureService.failureFindPopular();
        return ApiResTemplate.success(
                SuccessCode.FAILURE_FIND_POPULAR_SUCCESS,
                failures
        );
    }

    @PatchMapping("/{failureId}")
    @Operation(summary = "실패담 Id로 수정", description = "실패담 수정")
    public ApiResTemplate<Void> failureUpdate(@PathVariable("failureId") Long failureId, @RequestBody @Valid FailureUpdateRequestDto failureUpdateRequestDto) {
        failureService.failureUpdate(failureId, failureUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.FAILURE_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{failureId}")
    @Operation(summary = "실패담 삭제", description = "실패담 삭제")
    public ApiResTemplate<Void> failureDelete(@PathVariable("failureId") Long failureId) {
        failureService.failureDelete(failureId);
        return ApiResTemplate.successWithNoContent(SuccessCode.FAILURE_DELETE_SUCCESS);
    }
}

