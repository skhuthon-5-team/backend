package com.faillog.failure.application;


import com.faillog.common.exception.BusinessException;
import com.faillog.common.response.ErrorCode;
import com.faillog.failure.api.dto.request.FailureSaveRequestDto;
import com.faillog.failure.api.dto.request.FailureUpdateRequestDto;
import com.faillog.failure.api.dto.response.FailureInfoResponseDto;
import com.faillog.failure.api.dto.response.FailureListResponseDto;
import com.faillog.failure.domain.Failure;
import com.faillog.failure.domain.FailureCategory;
import com.faillog.failure.domain.repository.FailureRepository;
import com.faillog.user.domain.User;
import com.faillog.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FailureService {
    private final UserRepository userRepository;
    private final FailureRepository failureRepository;

    @Transactional
    public void failureSave(FailureSaveRequestDto failureSaveRequestDto) {
        User user = userRepository.findById(failureSaveRequestDto.userId())
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()));

        Failure failure = Failure.builder()
                .title(failureSaveRequestDto.title())
                .category(failureSaveRequestDto.category())
                .situation(failureSaveRequestDto.situation())
                .choice(failureSaveRequestDto.choice())
                .emotion(failureSaveRequestDto.emotion())
                .user(user)
                .build();

        failureRepository.save(failure);
    }

    public Page<FailureInfoResponseDto> failureFindUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()));

        return failureRepository.findByUser(user, pageable).
                map(FailureInfoResponseDto::from);
    }

    @Transactional
    public FailureInfoResponseDto failureFindById(Long failureId) {
        Failure failure = failureRepository.findById(failureId)
                .orElseThrow(()-> new BusinessException(
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION,
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION.getMessage()
                ));
        failure.increaseViewCount();
        return FailureInfoResponseDto.from(failure);
    }

    public Page<FailureInfoResponseDto> failureFindAll(Pageable pageable){
        return failureRepository.findAll(pageable)
                .map(FailureInfoResponseDto::from);
    }

    public Page<FailureInfoResponseDto> failureSearch(String keyword, Pageable pageable) {
        return failureRepository.findByTitleContaining(keyword, pageable)
                .map(FailureInfoResponseDto::from);
    }

    public Page<FailureInfoResponseDto> failureFindByCategory(FailureCategory category, Pageable pageable) {
        return failureRepository.findByCategory(category, pageable)
                .map(FailureInfoResponseDto::from);
    }

    public Page<FailureInfoResponseDto> failureFindPopular(Pageable pageable) {
        return failureRepository.findAll(pageable)
                .map(FailureInfoResponseDto::from);
    }

    @Transactional
    public void failureUpdate(Long failureId, FailureUpdateRequestDto failureUpdateRequestDto) {
        Failure failure = failureRepository.findById(failureId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION,
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION.getMessage()
                ));

        failure.update(failureUpdateRequestDto);
    }

    @Transactional
    public void failureDelete(Long failureId) {
        Failure failure = failureRepository.findById(failureId)
            .orElseThrow(() -> new BusinessException(
                    ErrorCode.FAILURE_NOT_FOUND_EXCEPTION,
                    ErrorCode.FAILURE_NOT_FOUND_EXCEPTION.getMessage()
            ));
        failureRepository.delete(failure);
    }
}
