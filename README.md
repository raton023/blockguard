# Landlord v17

Commmands:
	/landclaim <landname>	Claim a chunk.	
	/landunclaim <landname>	Unclaim a chunk.	
	/landallow <friend>	add a player to your lands.	
	/landdisallow <friend>	remove a player from your lands.	
	/landallowlist	see the players in your allowed list	
	/landlist <player>	see the lands you own	
	/landtp <landname>	teleport to another land	
	/landtpset	set the teleport point to where you stand	
	/landgive <landname> <player>	give a land to another player	
	/landenter <landname> <the message>	set the enter message of a land	
	/landexit <landname> <the message>	set the leave message of a land
Permissions:
	chunkprotector.1 2 3 4 5 6 7 8 till 30	(default 3) how much chunks can a player claim	
	chunkprotector.unlimited	(default to op)to set a player to unlimited chunks	
	chunkprotector.bypass	(default to op)player with this permission will can use break 
	build and more on claimed chunks	
	chunkprotector.nobuild	(default false) this will set that player so he only can 
	build inside his claims	
	chunkprotector.nobreak	(default false)	
	chunkprotector.nopay	(default op) player with this permission will not pay claim cost or taxes

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

 
