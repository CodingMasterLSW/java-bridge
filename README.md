## 다리 건너기

## 입력
- [ ] 자동으로 생성할 다리 길이를 입력받는다. (3이상 20 이하)
  - Exception
    - [ ] 공백이 입력되거나 숫자가 아닐 경우, 예외를 발생시킨다.
    - [ ] 3이상 20 이하의 숫자가 아닐경우, 예외를 발생시킨다.

- [ ] 이동할 칸을 입력받는다. (U or D)
  - Exception
    - [ ] U 혹은 D가 아닐경우, 예외를 발생시킨다.

- [ ] 게임 재시작/종료 여부를 입력받는다. (R or Q)
  - Exception
    - [ ] R 혹은 Q가 아닐경우, 예외를 발생시킨다.

## 다리 생성하기
- [ ] 다리를 생성할 때 위 칸과 아래칸 중 건널 수 있는 칸은 0과 1 무작위 값을 이용한다.
- [ ] 무작위 값이 0인 경우 아래칸, 1인 경우 위 칸이 건널 수 있는 칸으로 설계한다.

## 다리 건너기
- [ ] 입력값을 받아 다리를 건넌다. 건널 수 있다면 O, 건널수 없다면 X로 표시한다.
    
### 출력
- [ ] 최종 게임 결과를 출력한다.
- [ ] 게임 성공 여부를 출력한다.
- [ ] 총 시도한 횟수를 출력한다.