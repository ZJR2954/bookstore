#-*- coding:utf-8-*-
#!/user/bin/env python

import pygame
import time
import random


from pygame.locals import*
pygame.init()


class PlayerPlane(object):                                                  #自己的飞机
    def __init__(self,screen):
                                                 

        planeImageName ='./feiji/hero.gif'                                 #导入飞机的图片
        self.image = pygame.image.load(planeImageName).convert()           #获取飞机的图片
        winImageName ='./feiji/win.jpg'                                    #导入胜利结束的图片
        self.winImage = pygame.image.load(winImageName).convert()          #获取胜利结束的图片

        self.x = 230                                                        #飞机位置
        self.y = 600
        self.bullet = []                                                    #子弹
        self.chuangkou=screen

        self.speed = 4
        self.planeName = 'player'                                           #名字
        self.hit = 0                                                        #控制是否中弹 0=不中弹；1=中弹
        self.bomb_list = []                                                 #存储爆炸时需要的图片
        self.bomb_list.append(pygame.image.load("./feiji/enemy0_down1.png"))
        self.bomb_list.append(pygame.image.load("./feiji/enemy0_down2.png"))
        self.bomb_list.append(pygame.image.load("./feiji/enemy0_down3.png"))
        self.bomb_list.append(pygame.image.load("./feiji/enemy0_down4.png"))
        self.bomb_list.append(pygame.image.load("./feiji/lose.jpg"))        #失败时结束的图片
        self.image_num =0                                                   
        self.image_index = 0                                                #被子弹打中几次
        

    def draw(self):                                                         #画出飞机
        if self.hit == 1:                                                   #当被子弹打中时
            self.chuangkou.blit(self.bomb_list[self.image_index],(self.x,self.y))           #爆炸显示
            time.sleep(0.1)
            self.image_num += 1
            if self.image_num==7:
                self.image_num=0
                self.image_index+=1
                self.hit =0
            if self.image_index==4:                                          #当中弹了4次后，game over
                self.chuangkou.blit(self.bomb_list[self.image_index],(165,385))      #显示失败的图片
                pygame.display.update()
                time.sleep(3)
                exit()                                                      #结束程序
        else:                                                               #当没有被子弹打中时
            self.chuangkou.blit(self.image,(self.x,self.y))
        for temp in self.bullet:
           temp.draw()

    def keyhandle(self,keyValue):                                           #接受键盘信息并移动（上下左右或w s a d）
        if keyValue=='left':
            print("--按下 左键--")
            self.x -= 20
        elif keyValue=='right':
            print("--按下 右键--")
            self.x += 20
        elif keyValue=='space':
            print("--按下 空格键--")
            self.bullet.append (Bullet(self.chuangkou,self.planeName,self.x+40,self.y-15))

            self.draw()


    def bomb(self):                                                         #中弹后
        self.hit=1
    def win (self):                                                         #胜利后
        self.chuangkou.blit(self.winImage,(165,385))

        

class Bullet(object):                                                       #子弹类
    def __init__(self,screen,planeName,x,y):
        print("调用了初始化方法,发出子弹。。。")


        if planeName=="enemy":                                              #敌人的子弹
            zidanFile = './feiji/bullet-1.gif'


        elif planeName=="player":                                           #我的子弹
            zidanFile = './feiji/bullet-3.gif'

        self.zidan = pygame.image.load(zidanFile).convert()                 #接收子弹的图片

        self.x=x
        self.y=y
        self.chuangkou=screen
        self.planeName=planeName

    def draw(self):                                                         #画出子弹
        if self.planeName=="enemy":
            self.y +=4 
            if self.y<player.y+2 and self.y>=player.y-2 and self.x+5<=player.x+100 and self.x+5 >=player.x:  #如果自己中弹
                player.bomb()           
        elif self.planeName=="player":
            self.y -= 4
            if self.y<enemy.y+2 and self.y>=enemy.y-2 and self.x+5<=enemy.x+51 and self.x+5 >=enemy.x:
                player.win()                                                                                #如果敌机中弹
                pygame.display.update()
                time.sleep(3)
                exit()                                                                                      #退出程序

        self.chuangkou.blit(self.zidan,(self.x,self.y))



class Enemy(object):                                                       #敌机类
    def __init__(self,screen,x=0,y=0):

        self.x=x
        self.y=y

        enemyImageFile = './feiji/enemy-1.gif'                           #导入敌机的图片
        self.image=pygame.image.load(enemyImageFile).convert()

        self.fangxiang = 'right'                                        #初始化运动方向向右
        self.bullet = []
        self.chuangkou = screen
        self.planeName = 'enemy'

    def draw(self):                                                     #画出敌机位置
        screen.blit(self.image,(self.x,self.y))
        for temp in self.bullet:
           temp.draw()

    def move(self):                                                     #敌机移动方法
        if self.fangxiang == "right":
            self.x +=2
        elif self.fangxiang == "left" :
            self.x-=2

        if self.x>429:
            self.fangxiang = 'left'
        elif self.x<0:
            self.fangxiang = 'right'

        randomNum = random.randint(1,100)                                       #发射子弹的频率
        if randomNum in [75]:
            self.bullet.append (Bullet(self.chuangkou,self.planeName,self.x+25,self.y+39))

if __name__=='__main__':

    screen = pygame.display.set_mode((480,852),0,32)                            #创建窗口
    pygame.display.set_caption("飞机大战！")
    bgImageFile = './feiji/background.png'                                      #导入背景图片
    background = pygame.image.load(bgImageFile).convert()                       #获取背景图片
    player = PlayerPlane(screen)                                                #创建一个飞机
    enemy = Enemy(screen)                                                       #创建一个敌机

    while True:
       screen.blit(background,(0,0))                                            #背景图出现到窗口
       player.draw()                                                            #画出我的飞机的位置
       enemy.move()                                                             #敌机移动
       enemy.draw()                                                             #画出敌机的位置


       for event in pygame.event.get():
           if event.type == QUIT:                                               #关闭窗口并退出程序
               print ("exit")
               exit()
           elif event.type == KEYDOWN:                                          #从键盘收到指令（上下左右 或 w s a d）
               if event.key == K_a or event.key == K_LEFT:
                   print('left')
                   player.keyhandle("left")
               elif event.key == K_d or event.key == K_RIGHT:
                   print('right')
                   player.keyhandle("right")
               elif event.key == K_SPACE:
                   print('space')
                   player.keyhandle("space")
               

       pygame.display.update()                                                  #窗口显示更新
       time.sleep(0.01)                                                         #降低CPU
