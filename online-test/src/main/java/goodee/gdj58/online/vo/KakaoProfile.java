package goodee.gdj58.online.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class KakaoProfile {
	private String id;
	private Properties properties;
	private KakaoAccount kakao_account;

	@Data
	@JsonIgnoreProperties(ignoreUnknown=true)
	public class Properties {
		private String nickname;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown=true)
	public class KakaoAccount {
		private Profile profile;
		private String email;

		@Data
		@JsonIgnoreProperties(ignoreUnknown=true)
		public class Profile {
			private String nickname;
		}
	}
}
