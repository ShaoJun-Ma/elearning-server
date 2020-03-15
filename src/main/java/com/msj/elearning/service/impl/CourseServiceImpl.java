package com.msj.elearning.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.dto.*;
import com.msj.elearning.mapper.*;
import com.msj.elearning.pojo.*;
import com.msj.elearning.service.CourseService;
import com.msj.elearning.utils.PageUtils;
import com.msj.elearning.utils.TimeDiffUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("course")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseChapterMapper courseChapterMapper;
    @Autowired
    private CourseEvaluationMapper courseEvaluationMapper;
    @Autowired
    private ChapterStatusMapper chapterStatusMapper;
    @Value("${faceImgUrl}")
    private String faceImgUrl;

    /**
     * 获取首页的数据
     * @return
     */
    @Override
    public ServiceResult getHomeInfo() {
        //1、获取课程类型
        List<CourseTypeDTO> courseTypeList = getCourseTypeList();
        //2、获取最新课程
        List<CourseDTO> newCourseList = getNewCourseList(0);
        //3、获取免费课程
        List<CourseDTO> freeCourseList = getCourseListByIsFree(1);
        //4、获取付费课程
        List<CourseDTO> payCourseList = getCourseListByIsFree(0);
        HomeInfoDTO homeInfoDTO = new HomeInfoDTO(courseTypeList, newCourseList, freeCourseList, payCourseList);
        if (homeInfoDTO == null) {
            return new ServiceResult(false, "获取首页数据失败");
        }
        return new ServiceResult(true, "获取首页数据成功", homeInfoDTO);
    }

    /**
     * 获取列表的数据
     *
     * @param isFree
     * @return
     */
    @Override
    public ServiceResult getListInfo(Integer isFree,Integer currentPage,Integer pageSize) {
        //1、获取课程类型
        List<CourseTypeDTO> courseTypeDTOList = new ArrayList<>();
        List<CourseType> parentCourseTypeList = courseTypeMapper.findCourseTypeByParentId(0);
        for (CourseType p : parentCourseTypeList) {
            List<CourseType> childCourseType = courseTypeMapper.findCourseTypeByParentId(p.getId());
            CourseTypeDTO courseTypeDTO = new CourseTypeDTO(p.getId(), p.getName(), childCourseType);
            if(courseTypeDTO != null){
                courseTypeDTOList.add(courseTypeDTO);
            }
        }

        //2、获取课程
        //2.1 开启分页，封装 courseList 到 PageInfo 中
        PageHelper.startPage(currentPage,pageSize);
        List<Course> courseList = courseMapper.findCourseByIsFree(isFree);
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList);
        //2.2 将 PageInfo<Course> 转换为 PageInfo<CourseDTO>
        PageInfo<CourseDTO> courseDTOPageInfo = PageUtils.PageInfo2PageInfoDTO(coursePageInfo);
        // 2.3 将 PageInfo<CourseDTO> 放到 PageInfo<CourseDTO> 中
        List<CourseDTO> courseDTOList = null;
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        courseDTOPageInfo.setList(courseDTOList);

        ListInfoDTO listInfoDTO = new ListInfoDTO(courseTypeDTOList,courseDTOPageInfo);
        if(listInfoDTO == null){
            return new ServiceResult(false,"获取列表页数据失败");
        }
        return new ServiceResult(true,"获取列表页数据成功",listInfoDTO);
    }

    /**
     * 获取课程
     * @param ptId    课程父类型 （方向）
     * @param ctId    课程子类型 （分类）
     * @param rank   课程难度
     * @param isFree 是否免费
     */
    @Override
    public ServiceResult getCourseByPtIdAndCtIdAndRank(Integer ptId, Integer ctId, String rank, Integer isFree,
                                                     Integer currentPage,Integer pageSize) {
        //1、开启分页
        PageHelper.startPage(currentPage,pageSize);
        //2、获取课程
        List<Course> courseList = getCourseList(ptId,ctId,rank,isFree);
        //3、将 courseList 封装成 PageInfo 对象
        PageInfo<Course> coursePageInfoList = new PageInfo<>(courseList);
        //4、将 PageInfo<Course> 转化为 PageInfo<CourseDTO>
        PageInfo<CourseDTO> courseDTOPageInfo = PageUtils.PageInfo2PageInfoDTO(coursePageInfoList);
        //5、封装 List<CourseDTO>
        List<CourseDTO> courseDTOList = null;
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null) {
            return new ServiceResult(false, "点击获取课程失败");
        }
        //6、将 courseDTOList 放入 PageInfo<CourseDTO> 中
        courseDTOPageInfo.setList(courseDTOList);
        return new ServiceResult(true,"点击获取课程成功",courseDTOPageInfo);
    }

    /**
     * 获取课程列表
     * @param ptId 父类型
     * @param ctId 子类型
     * @param rank 难度
     * @param isFree 是否免费
     * @return
     */
    private List<Course> getCourseList(Integer ptId, Integer ctId, String rank, Integer isFree){
        List<Course> courseList = null;
        if(ptId == 0){
            //1、 方向：全部
            if(ctId == 0){
                //1.1 分类：全部
                courseList = courseMapper.findCourseByRankAndIsFree(rank, isFree);
            }else{
                //1.2 分类：具体
                courseList = courseMapper.findCourseByCtIdAndRankAndIsFree(ctId,rank,isFree);
            }
        }else{
            //2、方向：具体
            if(ctId == 0){
                //2.1 分类：全部
                courseList = courseMapper.findCourseByPtIdAndRankAndIsFree(ptId,rank,isFree);
            }else{
                //2.2 分类：具体
                //判断 子类型所属的父类型是否跟pId相等
                if(ptId != ((courseTypeMapper.findCourseTypeById(ctId)).getParentId())){
                    return null;
                }
                courseList = courseMapper.findCourseByCtIdAndRankAndIsFree(ctId,rank,isFree);
            }
        }
        if(courseList == null){
            return null;
        }
        return courseList;
    }

    /**
     * 获取父类型
     * @param ctId 课程子类型id
     * @return
     */
    @Override
    public ServiceResult getParentType(Integer ctId) {
        CourseType courseType = courseTypeMapper.findCourseTypeById(ctId);
        if(courseType == null){
            return new ServiceResult(false,"获取课程类型失败");
        }
        return new ServiceResult(true,"获取课程类型成功",courseType);
    }

    /**
     * 获取课程详情
     * @param cId 课程id
     * @param uId 用户id
     * @return
     */
    @Override
    public ServiceResult getDetailInfo(Integer cId,Integer uId) {
        //1、获取详情信息
        CourseDetail courseDetail = courseDetailMapper.findCourseDetailByCid(cId);
        //2、获取课程信息
        Course course = courseMapper.findCourseById(cId);
        //3、整合课程类型
        CourseTypeOne2OneDTO courseType = mergeCourseType(course);
        //4、获取作者信息
        User author = userMapper.findUserById(courseDetail.getUId());
        author.setFaceImg(faceImgUrl+author.getFaceImg());
        author.setPassword(null);
        //5、整合课程章节
        List<CourseChapterDTO> courseChapterList = mergeCourseChapterDTOList(courseDetail.getId(),uId);
        DetailInfoDTO detailInfoDTO = new DetailInfoDTO(courseType, author, course, courseDetail, courseChapterList);
        if(detailInfoDTO == null){
            return new ServiceResult(false,"获取课程详情失败");
        }
        return new ServiceResult(true,"获取课程详情成功",detailInfoDTO);
    }

    /**
     * 获取课程评价
     * @param cId 课程id
     * @param currentPage 当前页码
     * @param pageSize 一页多少条
     * @return
     */
    @Override
    public ServiceResult getEvaluation(Integer cId,Integer currentPage,Integer pageSize) {
        //1、开启分页
        PageHelper.startPage(currentPage,pageSize);
        //2、获取评论
        List<CourseEvaluation> evaluationList = courseEvaluationMapper.findEvaluationByCId(cId);
        //3、将list封装为 PageInfo<CourseEvaluation>
        PageInfo<CourseEvaluation> evaluationPageInfo = new PageInfo<>(evaluationList);
        //4、PageInfo<CourseEvaluation> 类型 转 PageInfo<CourseEvaluationDTO>
        PageInfo<CourseEvaluationDTO> evaluationDTOPageInfo = PageUtils.PageInfo2PageInfoDTO(evaluationPageInfo);

        List<CourseEvaluationDTO> courseEvaluationDTOList = new ArrayList<>();
        for (CourseEvaluation e : evaluationList) {
            //5、获取评论者
            User user = userMapper.findUserById(e.getUId());
            user.setFaceImg(faceImgUrl+user.getFaceImg());
            String timeDiff = TimeDiffUtil.timeDiff(new Date(), e.getCreateTime());
            CourseEvaluationDTO courseEvaluationDTO = new CourseEvaluationDTO(e.getId(),user.getUsername(),user.getFaceImg(),e.getEvaluation(),timeDiff);
            courseEvaluationDTOList.add(courseEvaluationDTO);
        }
        //6、PageInfo<CourseEvaluationDTO> 添加list
        evaluationDTOPageInfo.setList(courseEvaluationDTOList);
        if(evaluationDTOPageInfo == null){
            return new ServiceResult(false,"获取用户评论失败");
        }
        return new ServiceResult(true,"获取用户评论成功",evaluationDTOPageInfo);
    }

    /**
     * 获取章节
     *
     * @param cdId 课程详情id
     * @return
     */
    @Override
    public ServiceResult getChapter(Integer cdId) {
        List<CourseChapterDTO> courseChapterDTOList = mergeCourseChapterDTOList(cdId, 0);
        if(courseChapterDTOList == null){
            return new ServiceResult(false,"获取课程章节失败");
        }
        return new ServiceResult(true,"获取课程章节成功",courseChapterDTOList);
    }

    /**
     * 整合章节
     * @param cdId 课程详情id
     * @param uId 用户id
     * @return
     */
    private List<CourseChapterDTO> mergeCourseChapterDTOList(Integer cdId,Integer uId){
        List<CourseChapterDTO> courseChapterDTOList = new ArrayList<>();
        //1、获取父章节
        List<CourseChapter> parentChapterList = courseChapterMapper.findChapterByCdIdAndParentId(cdId, 0);
        //2、根据父章节获取子章节
        for (CourseChapter p : parentChapterList) {
            List<CourseChapter> childChapterList = courseChapterMapper.findChapterByCdIdAndParentId(cdId, p.getId());
            if(uId != 0){
                for (CourseChapter c : childChapterList) {
                    ChapterStatus chapterStatus = chapterStatusMapper.findChapterStatus(uId, cdId, c.getId());
                    if(chapterStatus == null){
                        c.setIs_finished(0);
                    }else{
                        c.setIs_finished(chapterStatus.getIsFinished());
                    }
                }
            }
            CourseChapterDTO courseChapterDTO = new CourseChapterDTO(p.getId(),p.getTitle(),p.getIntroduction(),childChapterList);
            courseChapterDTOList.add(courseChapterDTO);
        }
        if(courseChapterDTOList == null){
            return null;
        }
        return courseChapterDTOList;
    }

    /**
     * 整合课程类型
     * @param course
     * @return
     */
    private CourseTypeOne2OneDTO mergeCourseType(Course course){
        CourseType childType = courseTypeMapper.findCourseTypeById(course.getCtId());
        CourseType parentType = courseTypeMapper.findCourseTypeById(childType.getParentId());
        CourseTypeOne2OneDTO courseType = new CourseTypeOne2OneDTO(childType.getName(), parentType.getName());
        if(courseType == null){
            return null;
        }
        return courseType;
    }


    /**
     * 获取课程类型
     * @return
     */
    private List<CourseTypeDTO> getCourseTypeList(){
        ArrayList<CourseTypeDTO> courseTypeDTOList = new ArrayList<>();
        //1、获取父级课程类型
        List<CourseType> parentCourseTypeList = courseTypeMapper.findCourseTypeByParentId(0);
        for (CourseType p : parentCourseTypeList) {
            //2、获取子级课程类型
            List<CourseType> childCourseType = courseTypeMapper.findCourseTypeByParentId(p.getId());
            CourseTypeDTO courseTypeDTO = new CourseTypeDTO(p.getId(),p.getName(),childCourseType);
            courseTypeDTOList.add(courseTypeDTO);
        }
        if(courseTypeDTOList == null){
            return null;
        }
        return courseTypeDTOList;
    }

    /**
     * 获取最新课程
     * @return
     */
    private List<CourseDTO> getNewCourseList(Integer isFree){
        List<CourseDTO> courseDTOList = null;
        List<Course> courseList = courseMapper.findCourseByIsFreeAndOrderByCreateTime(isFree);
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null){
            return null;
        }
        return courseDTOList;
    }

    /**
     * 获取课程
     * @param  isFree 是否免费
     * @return
     */
    private List<CourseDTO> getCourseListByIsFree(Integer isFree){
        List<CourseDTO> courseDTOList = null;
        //1、根据是否免费获取课程
        List<Course> courseList = courseMapper.findCourseByIsFree(isFree);
        //2、整合List<courseDTO>
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null){
            return null;
        }
        return courseDTOList;
    }

    /**
     * 整合课程
     * @param courseList 课程列表
     * @return
     */
    private List<CourseDTO> mergeCourseDTOList(List<Course> courseList){

        List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
        for (Course c : courseList) {
            CourseType courseType = courseTypeMapper.findCourseTypeById(c.getCtId());
            CourseDTO courseDTO = new CourseDTO(c,courseType.getName());
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
