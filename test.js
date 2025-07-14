import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  vus: 10,           // 동시 사용자 수
  duration: '30s',   // 테스트 시간
};

export default function () {
  const headers = { 'Content-Type': 'application/json' };

  // 포인트 적립 API 테스트
  const increasePayload = JSON.stringify({
    userId: 1,      // 사용자 ID
    amount: 100     // 포인트 양 (적립)
  });

  const decreasePayload = JSON.stringify({
    userId: 1,
    amount: 50      // 포인트 양 (차감)
  });

  // ✅ 포트 8081로 변경!
  http.post('http://localhost:8081/points/increase', increasePayload, { headers });
  http.post('http://localhost:8081/points/decrease', decreasePayload, { headers });

  sleep(1);
}
