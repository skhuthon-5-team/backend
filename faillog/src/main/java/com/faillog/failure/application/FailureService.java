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

    public List<FailureInfoResponseDto> failureFindUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.USER_NOT_FOUND_EXCEPTION,
                        ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage()));

        List<Failure> failures = failureRepository.findByUser(user);
        return failures.stream().map(FailureInfoResponseDto::from).toList();
    }

    public FailureInfoResponseDto failureFindById(Long failureId) {
        Failure failure = failureRepository.findById(failureId)
                .orElseThrow(()-> new BusinessException(
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION,
                        ErrorCode.FAILURE_NOT_FOUND_EXCEPTION.getMessage()
                ));
        return FailureInfoResponseDto.from(failure);
    }

    public FailureListResponseDto failureFindAll(){
        List<FailureInfoResponseDto> failures = failureRepository.findAll()
                .stream()
                .map(FailureInfoResponseDto::from)
                .toList();
        return FailureListResponseDto.from(failures);
    }

    public FailureListResponseDto failureSearch(String keyword) {
        List<FailureInfoResponseDto> failures =
                failureRepository.findByTitleContaining(keyword)
                        .stream()
                        .map(FailureInfoResponseDto::from)
                        .toList();
        return FailureListResponseDto.from(failures);
    }

    public FailureListResponseDto failureFindByCategory(FailureCategory category) {
        List<FailureInfoResponseDto> failures =
                failureRepository.findByCategory(category)
                        .stream()
                        .map(FailureInfoResponseDto::from)
                        .toList();
        return FailureListResponseDto.from(failures);
    }

    public FailureListResponseDto failureFindPopular() {
        List<FailureInfoResponseDto> failures =
                failureRepository.findAllByOrderByViewCountDesc()
                        .stream()
                        .map(FailureInfoResponseDto::from)
                        .toList();
        return FailureListResponseDto.from(failures);
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
