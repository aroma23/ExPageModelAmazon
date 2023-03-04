@Calc
Feature: Calc

  Scenario Outline: Multiply
    Given Calc is launched
    When OperandA: '<operandA>', Operator '<operator>', and  OperandB: '<operandB>' are clicked
    Then Validate result: '<result>' matches in display

    Examples:
      | operandA | operator | operandB | result |
      | 8        | x        | 3        | 24     |
