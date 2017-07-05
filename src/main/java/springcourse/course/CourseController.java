package springcourse.course;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springcourse.topic.Topic;

@RestController
@RequestMapping("/models")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method=RequestMethod.GET,value="/{prateek}")
	public String getProfiles(@PathVariable String prateek)
	{
		return "prateek";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/topics/upload")
	public String uploadFile(@RequestParam (value="file") MultipartFile file)throws Exception
	{
		System.out.println(file.getOriginalFilename());
		if(courseService.saveFile(file))
		return "true";
		else
		return "false";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/topics/download/{filename:.+}")
	public FileSystemResource getFile(@PathVariable String filename)
	{
		System.out.println(filename);
		
		return courseService.getFile(filename);
	}
	
	@RequestMapping("/topics/{id}/courses") 
	public List<Course> getAllCourses(@PathVariable String id)
	{
		return courseService.getAllCourses(id);	
	}
	@RequestMapping("topics/{topicId}/courses/{id}")
	public Course getCourse(@PathVariable String id)
	{
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable String topicId)
	{
		course.setTopic(new Topic(topicId,"",""));
		courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{topicId}/courses/{courseId}")
	public void updateCourse(@RequestBody Course course, @PathVariable String topicId,@PathVariable String courseId)
	{
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}/courses/{courseId}")
	public void deleteCourse(@PathVariable String courseId)
	{
		courseService.deleteCourse(courseId);
	}
}
