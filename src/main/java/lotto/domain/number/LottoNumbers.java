package lotto.domain.number;

import lotto.domain.ticket.WinningLottoTicket;
import lotto.exception.ErrorMessage;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {

    public static final int LOTTO_NUMBER_SIZE = 6;

    private Set<LottoNumber> numbers;

    private LottoNumbers() {
    }

    private LottoNumbers(final List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = new LinkedHashSet<>(numbers);
    }

    public static LottoNumbers autoCreate() {
        return new LottoNumbers(LottoNumbersGenerator.generate());
    }

    public static LottoNumbers manualCreate(final List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    private void validateNumbers(final List<LottoNumber> numbers) {
        if ((numbers == null) || (numbers.size() != LOTTO_NUMBER_SIZE)) {
            throw new IllegalArgumentException(String.format(ErrorMessage.REQUIRED_SIZE_OF_LOTTO_NUMBERS, LOTTO_NUMBER_SIZE));
        }
        verifyDuplication(numbers);
    }

    private void verifyDuplication(final List<LottoNumber> numbers) {
        Set<LottoNumber> numbersOfNotDuplicated = new HashSet<>(numbers);

        if (numbersOfNotDuplicated.size() < LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NUMBERS);
        }
    }

    public int matchCount(final WinningLottoTicket winningLottoTicket) {
        return Math.toIntExact(numbers.stream()
                .filter(winningLottoTicket::contains)
                .count());
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return this.numbers.contains(lottoNumber);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
