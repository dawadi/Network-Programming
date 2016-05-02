#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

/**
 * Rakul Mahenthiran
 * CENG 320 - Lab 7
 * Dec 1, 2015
*/
int main(void)
{
		int sockfd, portno, n;
  	struct sockaddr_in serv_addr;
		struct hostent *server;
		char buffer[256];
		char *pos;
		int choice, searchChoice;
		char authorName[256];
		char bookTitle[256];
		char bookTitle2[256];
		char subjectArea[256];
		char quantity[256];
		char price[256];

		portno = 40050;  //TODO: remove hardcoded port number
		sockfd = socket(AF_INET, SOCK_STREAM, 0);
  	server = gethostbyname("localhost"); //TODO: remove hardcoded server ip
	  if (server == NULL) {
			 fprintf(stderr,"ERROR, no such host\n");
			 exit(0);
	  }

	  bzero((char *) &serv_addr, sizeof(serv_addr));
	  serv_addr.sin_family = AF_INET;
	  bcopy((char *)server->h_addr,
	         (char *)&serv_addr.sin_addr.s_addr,
	         server->h_length);
	  serv_addr.sin_port = htons(portno);
	  connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr));

		printf("--- CENG 320 - Lab 7 ---\n--- Dennis Konieczek ---\n\n");
		printf("\n1.\tDisplay All\n");
		printf("2.\tAdd\n");
		printf("3.\tSearch\n");
		printf("4.\tUpdate\n");
		printf("5.\tQuit\n\n");
		printf("Enter choice: ");
		scanf("%d",&choice);

		switch( choice )
		{
		    case 1 : //display all
					write(sockfd,"1",strlen("1"));
				  break;
		    case 2 : //add
					bzero(authorName,256);
					fgets(authorName,255,stdin);
		    	printf("\tEnter author name: \n");
					bzero(authorName,256);
					fgets(authorName,255,stdin);
					if ((pos=strchr(authorName, '\n')) != NULL)
					    *pos = '\0';
		    	printf("\tEnter book title: \n");
					bzero(bookTitle,256);
					fgets(bookTitle,255,stdin);
					if ((pos=strchr(bookTitle, '\n')) != NULL)
					    *pos = '\0';
		    	printf("\tEnter subject area: \n");
					bzero(subjectArea,256);
					fgets(subjectArea,255,stdin);
					if ((pos=strchr(subjectArea, '\n')) != NULL)
					    *pos = '\0';
		    	printf("\tEnter quantity: \n");
					bzero(quantity,256);
					fgets(quantity,255,stdin);
					if ((pos=strchr(quantity, '\n')) != NULL)
					    *pos = '\0';
		    	printf("\tEnter price: \n");
					bzero(price,256);
					fgets(price,255,stdin);
					if ((pos=strchr(price, '\n')) != NULL)
					    *pos = '\0';

					bzero(buffer,256);
    	    sprintf(buffer,"%s,%s,%s,%s,%s,%s","2",authorName,bookTitle,subjectArea,price,quantity);
					write(sockfd,buffer,strlen(buffer));
		        break;
		    case 3 : //search
		    		printf("\t1.\tSearch by Author Name\n");
						printf("\t2.\tSearch by Subject Area\n");
						printf("\t3.\tSearch by Book Title\n");
						printf("\tEnter choice: ");
						scanf("%d",&searchChoice);
		        switch (searchChoice) {
		        	case 1 : //search by author name
							printf("\t\tEnter author name to search:\n");
							bzero(authorName,256);
							fgets(authorName,255,stdin);//bug
							fgets(authorName,255,stdin);
							if ((pos=strchr(authorName, '\n')) != NULL)
									*pos = '\0';
							bzero(buffer,256);
							sprintf(buffer,"%s,%s,%s","3","1",authorName);
						  write(sockfd,buffer,strlen(buffer));
		        		break;
		        	case 2 : //search by subject area
							printf("\t\tEnter subject area to search:\n");
							bzero(subjectArea,256);
							fgets(subjectArea,255,stdin);//bug
							fgets(subjectArea,255,stdin);
							if ((pos=strchr(subjectArea, '\n')) != NULL)
									*pos = '\0';
							bzero(buffer,256);
							sprintf(buffer,"%s,%s,%s","3","2",subjectArea);
							write(sockfd,buffer,strlen(buffer));
		        		break;
		        	case 3 : //search by book title
							printf("\t\tEnter book title to search:\n");
							bzero(bookTitle,256);
							fgets(bookTitle,255,stdin);//bug
							fgets(bookTitle,255,stdin);
							if ((pos=strchr(bookTitle, '\n')) != NULL)
									*pos = '\0';
						  bzero(buffer,256);
							sprintf(buffer,"%s,%s,%s","3","3",bookTitle);
							write(sockfd,buffer,strlen(buffer));
		        		break;
		        	default :
		        		printf("Error, Invalid option\n");
		        		break;
		        }
		        break;
		    case 4 : //update a specific book title
						printf("\t\tEnter book title to update:\n");
						bzero(bookTitle,256);
						fgets(bookTitle,255,stdin);//bug
						fgets(bookTitle,255,stdin);
						if ((pos=strchr(bookTitle, '\n')) != NULL)
								*pos = '\0';

						printf("\t1.\tUpdate title\n");
						printf("\t2.\tUpdate quantity\n");
						printf("\t3.\tUpdate price\n");
						printf("\tEnter choice: ");
						scanf("%d",&searchChoice);
		        switch (searchChoice) {
								case 1: //update existing title
										printf("\t\tEnter new title:\n");
										bzero(bookTitle2,256);
										fgets(bookTitle2,255,stdin);//bug
										fgets(bookTitle2,255,stdin);
										if ((pos=strchr(bookTitle2, '\n')) != NULL)
												*pos = '\0';
									  bzero(buffer,256);
										sprintf(buffer,"%s,%s,%s,%s","4","1",bookTitle,bookTitle2);
										write(sockfd,buffer,strlen(buffer));
										break;
								case 2: //update quantity
										printf("\t\tEnter new quantity:\n");
										bzero(quantity,256);
										fgets(quantity,255,stdin);//bug
										fgets(quantity,255,stdin);
										if ((pos=strchr(quantity, '\n')) != NULL)
												*pos = '\0';
										bzero(buffer,256);
										sprintf(buffer,"%s,%s,%s,%s","4","2",bookTitle,quantity);
										write(sockfd,buffer,strlen(buffer));
										break;
								case 3: //update price
										printf("\t\tEnter new price:\n");
										bzero(price,256);
										fgets(price,255,stdin);//bug
										fgets(price,255,stdin);
										if ((pos=strchr(price, '\n')) != NULL)
												*pos = '\0';
										bzero(buffer,256);
										sprintf(buffer,"%s,%s,%s,%s","4","3",bookTitle,price);
										write(sockfd,buffer,strlen(buffer));
										break;
								default:
										printf("Error, Invalid option\n");
											break;
						}
						break;
				case 5: //exit
				printf("Bye\n");
				return 0;
				break;
		    default :
		    	printf("Error, Invalid option\n");
		    	break;
		}

	 // Display results
	 bzero(buffer,256);
	 read(sockfd,buffer,255);
	 printf("%s\n",buffer);

	return 0;
}
