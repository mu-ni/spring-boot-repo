//package com.gemalto.aam.icp.dao;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.gemalto.aam.icp.datamodel.Image;
//import com.gemalto.aam.icp.datamodel.QImage;
//import com.google.common.collect.Lists;
//import com.querydsl.core.types.dsl.BooleanExpression;
//
//public class QueryFactory {
//	
//	@Autowired
//	private ImageRepository imageRepository;
//	
//	public List<Image> findAllReviewedImages(){
//		
//		BooleanExpression status0 = QImage.image.status.eq(0);
//		BooleanExpression status1 = QImage.image.status.eq(1);
//		BooleanExpression status16 = QImage.image.status.eq(16);
//		BooleanExpression status17 = QImage.image.status.eq(17);
//		BooleanExpression notExported = QImage.image.exported.eq(0);
//		
//		Iterable<Image> rst = imageRepository.findAll(notExported.and(status0).or(status1).or(status16).or(status17));
//		
//		return Lists.newArrayList(rst);
//		
//	}
//	
//	
//	public List<Image> findToBeReviewedImages(Long userId){//page request//update
//		
//		BooleanExpression checkOK = QImage.image.status.eq(15);
//		BooleanExpression checkNOK = QImage.image.status.eq(31);
//		BooleanExpression exceptUser = QImage.image.imageChecks.any().user.userId.ne(userId);
//		
//		Iterable<Image> rst = imageRepository.findAll(exceptUser.and(checkOK.or(checkNOK)));
//		
//		return Lists.newArrayList(rst);
//		
//	}
//
//}
