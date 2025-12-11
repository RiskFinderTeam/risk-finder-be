package org.riskfinderteam.riskfinder.risk.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.dto.CustomerMailDto;
import org.riskfinderteam.riskfinder.risk.repository.RiskRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskService{
    private final RiskRepository riskRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public CustomerFactorDto getTop3FeaturesById(Long id){
        return riskRepository.getTop3FeaturesBySkIdCurr(id);
    }

    @Override
    public void sendRiskAlertMail(String toEmail, Long customerId) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("[RiskFinder] 대출 계좌 집중 관리 안내 및 리스크 분석 리포트");

            CustomerMailDto dto = riskRepository.getCustomerMailData(customerId);

            String htmlContent = createHtmlContent(dto);
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            log.info("메일 발송 성공: {}", toEmail);

        } catch (MessagingException e) {
            log.error("메일 발송 실패: {}", toEmail, e);
            throw new RuntimeException("메일 발송 중 오류가 발생했습니다.");
        }
    }

    private String createHtmlContent(CustomerMailDto dto) {
        return """
            <html>
            <body style="font-family: 'Apple SD Gothic Neo', sans-serif; background-color: #f4f4f4; padding: 20px;">
                <div style="max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 30px; border-radius: 8px; border: 1px solid #ddd;">
                    <h2 style="color: #d9534f; text-align: center;">⚠️ 리스크 관리 알림</h2>
                    <p>안녕하세요, 고객님.</p>
                    <p>귀하의 대출 계좌에 대한 정기 리스크 분석 결과, <strong>'집중 관리'</strong>가 필요한 상태로 분석되어 안내드립니다.</p>
                    
                    <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">
                    
                    <h3 style="color: #333;">📊 분석 결과 요약</h3>
                    <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
                        <tr>
                            <td style="padding: 10px; border-bottom: 1px solid #eee; font-weight: bold; color: #555;">현재 리스크 등급</td>
                            <td style="padding: 10px; border-bottom: 1px solid #eee; color: #d9534f; font-weight: bold;">%s</td>
                        </tr>
                        <tr>
                            <td style="padding: 10px; border-bottom: 1px solid #eee; font-weight: bold; color: #555;">주요 위험 요인 (Top 3)</td>
                            <td style="padding: 10px; border-bottom: 1px solid #eee;">%s</td>
                        </tr>
                    </table>
                    
                    <div style="background-color: #fff3cd; padding: 15px; border-radius: 5px; color: #856404; font-size: 14px;">
                        <strong>💡 관리 조치 안내</strong><br>
                        원활한 신용 관리를 위해 담당자와의 상담을 권장드리며, 조기 상환 또는 상환 계획 재수립이 필요할 수 있습니다.
                    </div>
                    
                    <p style="margin-top: 30px; font-size: 12px; color: #999; text-align: center;">
                        본 메일은 RiskFinder 시스템에 의해 자동 발송되었습니다.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(dto.getGrade(), dto.getTop3Features());
    }
}
