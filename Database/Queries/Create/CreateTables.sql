CREATE TABLE [dbo].[Roles] (
	[RoleId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[Name] NVARCHAR(50) NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0

)

CREATE TABLE [dbo].[Users] (
 	[UserId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
 	[RoleId] INT NOT NULL,
 	[Name] NVARCHAR(55) NOT NULL,
 	[FirstName] NVARCHAR(55) NOT NULL,
 	[UserName] NVARCHAR(55) NOT NULL UNIQUE,
 	[Email] NVARCHAR(55) NOT NULL UNIQUE,
 	[PhoneNumber] NVARCHAR(12) NOT NULL UNIQUE,
 	
 	[IsDeleted] BIT NOT NULL DEFAULT 0,
 	
 	CONSTRAINT [FK_User_Role] FOREIGN KEY ([RoleId]) REFERENCES [Roles]([RoleId])
)

CREATE TABLE [dbo].[Publishers] (
	[PublisherId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[Name] NVARCHAR(55) NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0
)


CREATE TABLE [dbo].[Authors] (
	[AuthorId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[Name] NVARCHAR(55) NOT NULL,
	[FirstName] NVARCHAR(55) NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0
)


CREATE TABLE [dbo].[Categories] (
	[CategoryId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[Name] NVARCHAR(55) NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0
)


CREATE TABLE [dbo].[Books] (
	[BookId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[CategoryId] INT NOT NULL,
	[AuthorId] INT NOT NULL,
	[PublisherId] INT NOT NULL,
	[Title] NVARCHAR(55) NOT NULL,
	[ISBN] NVARCHAR(55) NOT NULL,
	[Pagecount] INT NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0,
	
	CONSTRAINT [FK_Book_Category] FOREIGN KEY ([CategoryId]) REFERENCES [Categories]([CategoryId]),
	CONSTRAINT [FK_Book_Author] FOREIGN KEY ([AuthorId]) REFERENCES [Authors]([AuthorId]),
	CONSTRAINT [FK_Book_Publisher] FOREIGN KEY ([PublisherId]) REFERENCES [Publishers]([PublisherId])
	
)


CREATE TABLE [dbo].[Reviews] (
	[ReviewId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[UserId] INT NOT NULL,
	[BookId] INT NOT NULL,
	[Score] INT NOT NULL,
	[Likes] INT NOT NULL,
	[Dislikes] INT NOT NULL,
	[Description] NVARCHAR(MAX) NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0,

	CONSTRAINT [FK_Review_User] FOREIGN KEY ([UserId]) REFERENCES [Users]([UserId]),
	CONSTRAINT [FK_Review_Book] FOREIGN KEY ([BookId]) REFERENCES [Books]([BookId]) 
)


CREATE TABLE [dbo].[Replies] (
	[ReplyId] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[ReviewId] INT NOT NULL,
	[ReplyText] NVARCHAR(MAX) NOT NULL,
	
	[IsDeleted] BIT NOT NULL DEFAULT 0,
	
	CONSTRAINT [FK_Reply_Review] FOREIGN KEY ([ReviewId]) REFERENCES [Reviews]([ReviewId])
)
