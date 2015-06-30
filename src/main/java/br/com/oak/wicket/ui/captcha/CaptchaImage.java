package br.com.oak.wicket.ui.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class CaptchaImage extends NonCachingImage {

	private static final long serialVersionUID = -7496734248495972223L;

	@SpringBean
	private DefaultKaptcha captchaProducer;

	public CaptchaImage(String id) {
		super(id);

		setImageResource(new DynamicImageResource() {

			private static final long serialVersionUID = 4979232245216614063L;

			@Override
			protected byte[] getImageData(Attributes attributes) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

				try {
					BufferedImage bi = getImageCaptchaService();
					encoder.encode(bi);
					return os.toByteArray();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			private BufferedImage getImageCaptchaService() {

				Request request = RequestCycle.get().getRequest();

				HttpServletRequest httpRequest = (HttpServletRequest) request
						.getContainerRequest();

				String capText = captchaProducer.createText();

				httpRequest.getSession().setAttribute(
						Constants.KAPTCHA_SESSION_KEY, capText);

				BufferedImage bi = captchaProducer.createImage(capText);

				return bi;
			}
		});
	}
}