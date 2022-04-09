package com.codesoom.assignment.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TaskEditDto 클래스")
class TaskEditDtoTest {

    @Nested
    @DisplayName("validate 메소드는")
    class Describe_validate {

        @Nested
        @DisplayName("할 일 제목의 값이 존재한다면")
        class Context_valid {

            final String validTitle = "TITLE";

            TaskEditDto subject() {
                return new TaskEditDto(validTitle);
            }

            @Test
            @DisplayName("예외를 던지지 않는다.")
            void it_not_throw_exception() {
                TaskEditDto taskEditDto = subject();

                String expected = String.format(TaskEditDto.TO_STRING_FORMAT, taskEditDto.getTitle());

                assertThat(taskEditDto.toString()).isEqualTo(expected);
            }
        }

        @Nested
        @DisplayName("할 일 제목이 ")
        class Context_invalid {

            @ParameterizedTest(name = "\"{0}\" 이라면 예외를 던진다.")
            @NullAndEmptySource
            @ValueSource(strings = {" "})
            void it_throw_exception(String givenTitle) {

                TaskEditDto taskEditDto = new TaskEditDto(givenTitle);
                assertThatThrownBy(
                        taskEditDto::validate
                ).isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
