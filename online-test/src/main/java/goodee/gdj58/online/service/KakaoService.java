package goodee.gdj58.online.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import goodee.gdj58.online.mapper.IdMapper;
import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.KakaoProfile;
import goodee.gdj58.online.vo.OAuthToken;
import goodee.gdj58.online.vo.Student;

@Transactional
@Service
public class KakaoService {
	@Autowired StudentMapper studentMapper;
	@Autowired IdMapper idMapper;
	
	public String getKakaoAccessToken(String code) {
		String clientId = "5994d10bf46ff448031d3bfa1d7939c1";
		String redirectUri = "http://localhost:80/online-test/login/kakao";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("redirect_uri", redirectUri);
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> responseEntity = 
				restTemplate.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		String accessToken = oauthToken.getAccess_token();
		System.out.println("accessToken------> " + accessToken);
		
		return accessToken;
	}
	
	public Student getKakaoProfile(String accessToken) {
		// https://kapi.kakao.com/v2/user/me
		// Authorization: Bearer ${ACCESS_TOKEN}/KakaoAK ${APP_ADMIN_KEY}
		// Content-type: application/x-www-form-urlencoded;charset=utf-8
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> userInfo = new HttpEntity<>(headers);
		
		ResponseEntity<String> responseEntity = 
				restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, userInfo, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper.readValue(responseEntity.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String userNo = kakaoProfile.getId();
		String userNickname = kakaoProfile.getProperties().getNickname();
		String userEmail = kakaoProfile.getKakao_account().getEmail();
		UUID uuid = UUID.randomUUID();
		
		// studentNo(auto), studentId, studentPw, studentName;
		String studentId = userEmail + "_" + userNo;
		String studentPw = userNo + "_" + uuid;
		String studentName = userNickname;
		int studentNo = studentMapper.selectStudentNoById(studentId);
		
		/*
		System.out.println("studenId : " + studentId);
		System.out.println("studentPw : " + studentPw);
		System.out.println("studentName : " + studentName);
		*/
		Student student = new Student();
		student.setStudentNo(studentNo);
		student.setStudentId(studentId);
		student.setStudentPw(studentPw);
		student.setStudentName(studentName);
		
		return student;
	}
	
	// -1 중복 1 성공 0 실패
	public int addKakaoUser(Student student) {
		String idCheck = idMapper.selectIdCheck(student.getStudentId());
		if(idCheck != null) { // 아이디 중복
			System.out.println("카카오 회원가입 중복");
			return -1;
		}
		
		int row = studentMapper.insertStudent(student);
		if (row != 1) {
			System.out.println("카카오 회원가입 실패");
			return row;
		}
		System.out.println("카카오 회원가입 성공");

		return row;
	}
	
}
