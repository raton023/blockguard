# Landlord v17

	COMMANDS	DESCRIPTION	
1	/landclaim <landname>	Claim a chunk.	
2	/landunclaim <landname>	Unclaim a chunk.	
3	/landallow <friend>	add a player to your lands.	
4	/landdisallow <friend>	remove a player from your lands.	
5	/landallowlist	see the players in your allowed list	
6	/landlist <player>	see the lands you own	
7	/landtp <landname>	teleport to another land	
8	/landtpset	set the teleport point to where you stand	
9	/landgive <landname> <player>	give a land to another player	
10	/landenter <landname> <the message>	set the enter message of a land	
11	/landexit <landname> <the message>	set the leave message of a land



	PERMISSIONS	DESCRIPTION	
1	chunkprotector.1 2 3 4 5 6 7 8 till 30	(default 3) how much chunks can a player claim	
2	chunkprotector.unlimited	(default to op)to set a player to unlimited chunks	
3	chunkprotector.bypass	(default to op)player with this permission will can use break build and more on claimed chunks	
4	chunkprotector.nobuild	(default false) this will set that player so he only can build inside his claims	
5	chunkprotector.nobreak	(default false)	
6	chunkprotector.nopay	(default op) player with this permission will not pay claim cost or taxes

Protected from:
- Placing Breaking Using Burning Exploded Moving(using pistons).
- Players Animals And Villagers protection inside Player Chunk
- ArmorStands, Frames & Paints.

Features:
- enter leave message on registered chunks
- send you a message when a player of your friends list join the server
- multi-world support
- Economy rent system charge all landlords every period of time (Default: 1 Minecraft Day)
Thanks to NemesiS_ITA

TO-DO:
- add chunkprotector.nopay permission
- add flags
- add MySQL
- Please comment any idea to add on next update
- add /landhelp and /landrename

 
