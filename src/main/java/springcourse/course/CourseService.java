package springcourse.course;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	private static String FILE_DIRECTORY_PATH="C:/Users/prateeksharma/Desktop";
	public List<Course> getAllCourses(String topicId)
	{
		//return this.topics;
		List<Course> topics= new ArrayList<Course>();
		/* courseRepository.findAll()
				.forEach(topics::add);
				*/
		courseRepository.findByTopicId(topicId)
		.forEach(topics::add);
		 return topics;
	}
	public Course getCourse(String id)
	{
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return courseRepository.findOne(id);
	}
	public void addCourse(Course course) {
		//topics.add(topic);
		courseRepository.save(course);
	}
	public void updateCourse(Course course) {
		/*for(int i=0;i<topics.size();i++)
		{
			if(topics.get(i).getId().equals(id))
			{
				topics.set(i, topic);
				return;
			}
		}*/
		
		courseRepository.save(course);
		
	}
	public void deleteCourse(String id) {
		courseRepository.delete(id);
	}
	public boolean saveFile(MultipartFile file)throws Exception {
		String destination = FILE_DIRECTORY_PATH+"/"+file.getOriginalFilename();
		File newFile= new File(destination);
		//file.transferTo(newFile);
		
		byte bytes[]=file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
		stream.write(bytes);
		stream.close();
		return true;
	}
	public FileSystemResource getFile(String filename) {
		File file = new File(FILE_DIRECTORY_PATH, filename);
		return new FileSystemResource(file);
	}
	
}
