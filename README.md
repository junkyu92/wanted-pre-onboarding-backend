# wanted-pre-onboarding-backend
Java & Spring Boot Project

JPA & MySql
 
MODEL

company
- company_id
- name
- country 
- region
  
user
- user_id
- name

job_listing
- job_listing_id
- company_id (company)
- position
- reward
- description
- tech_stack

apply
- apply_id
- job_listing_id (job_listing)
- user_id (user)

요구사항

1. 채용공고 등록

request - /api/v1/job-listing (POST)

	{
  
		"companyId":1,
		"position":"백엔드 주니어 개발자",
		"reward":1000000,
		"description":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
		"techStack":"Python"
    
	}

2. 채용공고 수정

request - /api/v1/job-listing (PATCH)

	{
	
		"jobListingId":1,
		"position":"백엔드 주니어 개발자",
		"reward":1500000, 
		"description":"원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", # 변경됨
		"techStack":"Python"
	
	}

3. 채용공고 삭제

request - /api/v1/job-listing (DELETE)
	
	{
	
		"jobListingId":1
	
	}

4-1. 채용공고 목록 조회

request - /api/v1/job-listings?page=0&size=5 (GET)

response

	[
	
		{
		
			"jobListingId": 채용공고_id,
			"name":"원티드랩",
			"country":"한국",
			"region":"서울",
			"position":"백엔드 주니어 개발자",
			"reward":1500000,
			"techStack":"Python"
		
		},
		...
	
	]
  
4-2. 채용공고 검색

request - /api/v1/job-listings/search?keyword=원티드&page=0&size=5 (GET)

response

	[
	
		{
		
			"jobListingId": 채용공고_id,
			"name":"원티드랩",
			"country":"한국",
			"region":"서울",
			"position":"백엔드 주니어 개발자",
			"reward":1500000,
			"techStack":"Python"
		
		},
		...
	
	]

5. 채용 상세 페이지

request - /api/v1/job-listing/1 (GET)

response

	{
	
		"jobListingId": 채용공고_id,
		"name":"원티드랩",
		"country":"한국",
		"region":"서울",
		"position":"백엔드 주니어 개발자",
		"reward":1500000,
		"techStack":"Python",
		"description":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
		"otherJobListing":[job_listing_id, job_listing_id, ..] id List
	
	}

6. 채용공고 지원

request - /api/v1/apply (POST)

	{
		"jobListingId": 채용공고_id,
		"userId": 사용자_id
	
	}













