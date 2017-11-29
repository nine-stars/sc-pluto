package com.iyb.ak.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iyb.ak.entity.base.MessageVo;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Slf4j
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {
    private final ResponseEntityDecoder decoder;

    public FeignErrorDecoder() {
        this.decoder = new ResponseEntityDecoder(new GsonDecoder());
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            ResponseEntity<MessageVo> error = (ResponseEntity<MessageVo>) decoder.decode(response, new TypeReference<ResponseEntity<MessageVo>>() {
            }.getType());

            int status = response.status();
            switch (status) {
                case 301:
                    return new MovedPermanentlyException(error.getBody(), methodKey);
                case 400:
                    return new BadRequestException(error.getBody(), methodKey);
                case 401:
                    return new UnauthorizedException(error.getBody(), methodKey);
                case 403:
                    return new ForbiddenException(error.getBody(), methodKey);
                case 404:
                    return new NotFoundException(error.getBody(), methodKey);
                case 500:
                    return new BusinessException(error.getBody(), methodKey);
                default:
                    return new BusinessException(error.getBody(), methodKey);
            }
        } catch (Exception e) {
            log.error(methodKey + " error:" + response.toString());
            log.error("FeignErrorDecoder error:" + e.getMessage());
            return new BusinessException(BaseException.ERR_9999, this);
        }
    }

}
