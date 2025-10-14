CREATE TABLE `Counters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `Action` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `type` varchar(11) NOT NULL,
                          `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `ActionRecord` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `actionId` int(11) NOT NULL,
    `operator` varchar(512) NOT NULL,
    `detail` text NOT NULL,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Recipe` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `name` int(11) NOT NULL,
                                `operator` varchar(512) NOT NULL,
                                `detail` varchar(512) NOT NULL,
                                `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `ActionRecordDesc` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `actionRecordId` int(11) NOT NULL,
                                `detail` varchar(512) NOT NULL,
                                `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8