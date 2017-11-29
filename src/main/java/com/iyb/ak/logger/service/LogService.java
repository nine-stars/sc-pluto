package com.iyb.ak.logger.service;

/**
 * Author : fanjun
 * Date   : 2017/8/1
 * Fouction : 日志保存到mongodb的service
 */
/*@ConditionalOnClass(MongoClient.class)
@Service
@Slf4j*/
public class LogService {
/*	@Autowired(required = false)
	private MongoTemplate mongoTemplate;

	@Async
	public void save(BaseLogInfo logInfo) {
		try {
			if(mongoTemplate == null) {
				log.debug("mongodb template is null!");
				return;
			}
			String collectionName = logInfo.getType() + "_" + LocalDate.now().toString().replace("-", "");
			mongoTemplate.insert(logInfo, collectionName);
//			log.debug("save " + BaseLogInfo.class.getSimpleName() + " record success.");
		} catch (Exception e) {
			log.error("save " + BaseLogInfo.class.getSimpleName() + " error.", e);
		}
	}*/

}
