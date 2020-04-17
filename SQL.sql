CREATE DATABASE online_education;

USE online_education;

#Lecturer 
CREATE TABLE `edu_lecturer` (
  `id` char(19) NOT NULL COMMENT 'Lecturer ID',
  `name` varchar(20) NOT NULL COMMENT 'Lecturer Name',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT 'Lecturer introduction',
  `career` varchar(500) DEFAULT NULL COMMENT 'Lecturer brief summary in one sentence',
  `level` int(10) unsigned NOT NULL COMMENT 'Lecturer Level, 1 is junior, 2 is senior',
  `avatar` varchar(255) DEFAULT NULL COMMENT 'Lecturer avatar',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sort',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'Logical deletion 1（true）deleted， 0（false）not deleted',
  `gmt_create` datetime NOT NULL COMMENT 'Row or record creation time ',
  `gmt_modified` datetime NOT NULL COMMENT 'Row or record modification time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Lecturer';


#Course Subject
CREATE TABLE `edu_subject` (
  `id` char(19) NOT NULL COMMENT 'Course Subject ID',
  `title` varchar(10) NOT NULL COMMENT 'Course Subject Name',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT 'Subject Parent ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sort',
  `gmt_create` datetime NOT NULL COMMENT 'Create Time',
  `gmt_modified` datetime NOT NULL COMMENT 'Update Time',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='Course Subject';

SELECT * FROM edu_subject;

# Course 
CREATE TABLE `edu_course` (
  `id` char(19) NOT NULL COMMENT 'Course ID',
  `lecturer_id` char(19) NOT NULL COMMENT 'Course Lecturer ID',
  `subject_id` char(19) NOT NULL COMMENT 'Course Subject ID',
  `subject_parent_id` char(19) NOT NULL COMMENT 'Course Subject Parent ID',
  `title` varchar(100) NOT NULL COMMENT 'Course Title',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT 'Course Price, 0 is free',
  `lesson_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Total Lessons Number',
  `cover` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT 'Course Cover Path',
  `sell_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sell Count',
  `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT 'View Count',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT 'Version',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT 'Course Status Draft not published, Published means published',
  `is_deleted` tinyint(3) DEFAULT NULL COMMENT 'Logic Deletion 1（true）deleted， 0（false）not deleted',
  `gmt_create` datetime NOT NULL COMMENT 'Create Time',
  `gmt_modified` datetime NOT NULL COMMENT 'Update Time',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_lecturer_id` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='Course Table';

select * from edu_course;

DELETE FROM edu_course;
commit;

ALTER  TABLE edu_course MODIFY title varchar(100);
describe edu_course;

SELECT * FROM edu_course;

#
# Structure for table "edu_chapter"
#

CREATE TABLE `edu_chapter` (
  `id` char(19) NOT NULL COMMENT 'Chapter ID',
  `course_id` char(19) NOT NULL COMMENT 'Course ID',
  `title` varchar(50) NOT NULL COMMENT 'Chapter Name',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sort',
  `gmt_create` datetime NOT NULL COMMENT 'Create Time',
  `gmt_modified` datetime NOT NULL COMMENT 'Update Time',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='Course Chapter';

#
# Structure for table "edu_course_description"
#
CREATE TABLE `edu_course_description` (
  `id` char(19) NOT NULL COMMENT 'Course ID',
  `description` text COMMENT 'Course Description',
  `gmt_create` datetime NOT NULL COMMENT 'Create Time',
  `gmt_modified` datetime NOT NULL COMMENT 'Update Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Course Description';

#
# Structure for table "edu_video"
#

CREATE TABLE `edu_section` (
  `id` char(19) NOT NULL COMMENT 'Section ID',
  `course_id` char(19) NOT NULL COMMENT 'Course ID',
  `chapter_id` char(19) NOT NULL COMMENT 'Chapter ID',
  `title` varchar(50) NOT NULL COMMENT 'Section Title',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT 'Video Cloud Source ID',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT 'Video Original Name',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Sort',
  `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'Times of play',
  `is_free` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'Is Free? 0 is free 1 is not free',
  `video_duration` float NOT NULL DEFAULT '0' COMMENT 'Video Length',
  `video_status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Three Values: Empty, Transcoding, Normal; Empty means not upload',
  `video_size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'Video Size',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT 'Version',
  `gmt_create` datetime NOT NULL COMMENT 'Create Time',
  `gmt_modified` datetime NOT NULL COMMENT 'Update Time',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='Course Section with Video Information';
SELECT * From edu_section;

DELETE FROM edu_section;
commit;

SELECT * From edu_lecturer;
SELECT * From edu_course;
SELECT * FROM edu_subject;
SELECT * FROM edu_lecturer WHERE gmt_create between "2020-03-28 09:00:00" AND "2020-03-28 10:00:00";