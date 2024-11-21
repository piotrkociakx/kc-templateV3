

package pl.piotrkociakx.util.player


import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.*
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.Block
import org.bukkit.block.PistonMoveReaction
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.entity.*
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.*
import org.bukkit.map.MapView
import org.bukkit.metadata.MetadataValue
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.permissions.PermissionAttachmentInfo
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.util.Vector
import pl.piotrkociakx.util.chat.ChatUtil
import pl.piotrkociakx.util.hook.Hook
import java.net.InetSocketAddress
import java.util.*

@Suppress( "UNUSED", "DEPRECATION", "DEPRECATION")
class BPlayer(private val player: Player) {

    fun sendMessage(message: String) {
        val processedMessage = processMessage(message)
        player.sendMessage(processedMessage)
    }

    fun sendTitle(title: String, subtitle: String?) {
        val processedTitle = processMessage(title)
        val processedSubtitle = processMessage(subtitle ?: "")
        player.sendTitle(processedTitle, processedSubtitle)
    }

    fun sendTitle(title: String) {
        val processedTitle = processMessage(title)
        player.sendTitle(processedTitle, null)
    }

    private fun processMessage(message: String): String {
        var coloredMessage = ChatUtil.replaceColors(message)
        coloredMessage = ChatUtil.replaceHexColors(coloredMessage)
        coloredMessage = ChatUtil.replacePlaceholders(player, coloredMessage)

        return if (Hook.isPlaceHolderAPI()) {
            PlaceholderAPI.setPlaceholders(player, coloredMessage)
        } else {
            coloredMessage
        }
    }

    fun getLineOfSight(set: Set<Material>, i: Int): List<Block> {
        return player.getLineOfSight(set, i)
    }



    fun getDisplayName(): String {
        return player.displayName
    }


    fun getPlayerListName(): String {
        return player.playerListName
    }


    fun setCompassTarget(location: Location) {
        player.compassTarget = location
    }

    fun getCompassTarget(): Location {
        return player.compassTarget
    }

    fun getAddress(): InetSocketAddress? {
        return player.address
    }

    fun sendRawMessage(s: String) {
        player.sendRawMessage(s)
    }

    fun kickPlayer(s: String) {
        player.kickPlayer(s)
    }

    fun chat(s: String) {
        player.chat(s)
    }

    fun performCommand(s: String): Boolean {
        return player.performCommand(s)
    }

    fun isSneaking(): Boolean {
        return player.isSneaking
    }

    fun setSneaking(b: Boolean) {
        player.isSneaking = b
    }

    fun isSprinting(): Boolean {
        return player.isSprinting
    }

    fun setSprinting(b: Boolean) {
        player.isSprinting = b
    }

    fun saveData() {
        player.saveData()
    }

    fun loadData() {
        player.loadData()
    }

    fun setSleepingIgnored(b: Boolean) {
        player.isSleepingIgnored = b
    }

    fun isSleepingIgnored(): Boolean {
        return player.isSleepingIgnored
    }

    fun playNote(location: Location, b: Byte, b1: Byte) {
        player.playNote(location, b, b1)
    }

    fun playNote(location: Location, instrument: Instrument, note: Note) {
        player.playNote(location, instrument, note)
    }

    fun playSound(location: Location, sound: Sound, v: Float, v1: Float) {
        player.playSound(location, sound, v, v1)
    }

    fun playSound(location: Location, s: String, v: Float, v1: Float) {
        player.playSound(location, s, v, v1)
    }

    fun playSound(location: Location, sound: Sound, soundCategory: SoundCategory, v: Float, v1: Float) {
        player.playSound(location, sound, soundCategory, v, v1)
    }

    fun playSound(location: Location, s: String, soundCategory: SoundCategory, v: Float, v1: Float) {
        player.playSound(location, s, soundCategory, v, v1)
    }

    fun stopSound(sound: Sound) {
        player.stopSound(sound)
    }

    fun stopSound(s: String) {
        player.stopSound(s)
    }

    fun stopSound(sound: Sound, soundCategory: SoundCategory) {
        player.stopSound(sound, soundCategory)
    }

    fun stopSound(s: String, soundCategory: SoundCategory) {
        player.stopSound(s, soundCategory)
    }

    fun playEffect(location: Location, effect: Effect, i: Int) {
        player.playEffect(location, effect, i)
    }

    fun <T : Any?> playEffect(location: Location, effect: Effect, t: T) {
        player.playEffect(location, effect, t)
    }

    fun sendBlockChange(location: Location, material: Material, b: Byte) {
        player.sendBlockChange(location, material, b)
    }

    fun sendSignChange(location: Location, strings: Array<out String>) {
        player.sendSignChange(location, strings)
    }

    fun sendMap(mapView: MapView) {
        player.sendMap(mapView)
    }

    fun updateInventory() {
        player.updateInventory()
    }


    fun incrementStatistic(statistic: Statistic) {
        player.incrementStatistic(statistic)
    }

    fun decrementStatistic(statistic: Statistic) {
        player.decrementStatistic(statistic)
    }

    fun incrementStatistic(statistic: Statistic, i: Int) {
        player.incrementStatistic(statistic, i)
    }

    fun decrementStatistic(statistic: Statistic, i: Int) {
        player.decrementStatistic(statistic, i)
    }

    fun setStatistic(statistic: Statistic, i: Int) {
        player.setStatistic(statistic, i)
    }

    fun getStatistic(statistic: Statistic): Int {
        return player.getStatistic(statistic)
    }

    fun incrementStatistic(statistic: Statistic, material: Material) {
        player.incrementStatistic(statistic, material)
    }

    fun decrementStatistic(statistic: Statistic, material: Material) {
        player.decrementStatistic(statistic, material)
    }

    fun getStatistic(statistic: Statistic, material: Material): Int {
        return player.getStatistic(statistic, material)
    }

    fun incrementStatistic(statistic: Statistic, material: Material, i: Int) {
        player.incrementStatistic(statistic, material, i)
    }

    fun decrementStatistic(statistic: Statistic, material: Material, i: Int) {
        player.decrementStatistic(statistic, material, i)
    }

    fun setStatistic(statistic: Statistic, material: Material, i: Int) {
        player.setStatistic(statistic, material, i)
    }

    fun incrementStatistic(statistic: Statistic, entityType: EntityType) {
        player.incrementStatistic(statistic, entityType)
    }

    fun decrementStatistic(statistic: Statistic, entityType: EntityType) {
        player.decrementStatistic(statistic, entityType)
    }

    fun getStatistic(statistic: Statistic, entityType: EntityType): Int {
        return player.getStatistic(statistic, entityType)
    }

    fun incrementStatistic(statistic: Statistic, entityType: EntityType, i: Int) {
        player.incrementStatistic(statistic, entityType, i)
    }

    fun decrementStatistic(statistic: Statistic, entityType: EntityType, i: Int) {
        player.decrementStatistic(statistic, entityType, i)
    }

    fun setStatistic(statistic: Statistic, entityType: EntityType, i: Int) {
        player.setStatistic(statistic, entityType, i)
    }

    fun setPlayerTime(l: Long, b: Boolean) {
        player.setPlayerTime(l, b)
    }

    fun getPlayerTime(): Long {
        return player.playerTime
    }

    fun getPlayerTimeOffset(): Long {
        return player.playerTimeOffset
    }

    fun isPlayerTimeRelative(): Boolean {
        return player.isPlayerTimeRelative
    }

    fun resetPlayerTime() {
        player.resetPlayerTime()
    }


    fun getPlayerWeather(): WeatherType? {
        return player.playerWeather
    }

    fun resetPlayerWeather() {
        player.resetPlayerWeather()
    }

    fun giveExp(i: Int) {
        player.giveExp(i)
    }

    fun giveExpLevels(i: Int) {
        player.giveExpLevels(i)
    }

    fun getExp(): Float {
        return player.exp
    }

    fun setExp(v: Float) {
        player.exp = v
    }

    fun getLevel(): Int {
        return player.level
    }

    fun setLevel(i: Int) {
        player.level = i
    }

    fun getTotalExperience(): Int {
        return player.totalExperience
    }

    fun setTotalExperience(i: Int) {
        player.totalExperience = i
    }

    fun getExhaustion(): Float {
        return player.exhaustion
    }

    fun setExhaustion(v: Float) {
        player.exhaustion = v
    }

    fun getSaturation(): Float {
        return player.saturation
    }

    fun setSaturation(v: Float) {
        player.saturation = v
    }

    fun getFoodLevel(): Int {
        return player.foodLevel
    }

    fun setFoodLevel(i: Int) {
        player.foodLevel = i
    }

    fun getBedSpawnLocation(): Location? {
        return player.bedSpawnLocation
    }

    fun setBedSpawnLocation(location: Location?) {
        player.bedSpawnLocation = location
    }

    fun setBedSpawnLocation(location: Location?, b: Boolean) {
        player.setBedSpawnLocation(location, b)
    }

    fun getAllowFlight(): Boolean {
        return player.allowFlight
    }

    fun setAllowFlight(b: Boolean) {
        player.allowFlight = b
    }

    fun hidePlayer(player: Player) {
        this.player.hidePlayer(player)
    }

    fun hidePlayer(plugin: Plugin, player: Player) {
        this.player.hidePlayer(plugin, player)
    }

    fun showPlayer(player: Player) {
        this.player.showPlayer(player)
    }

    fun showPlayer(plugin: Plugin, player: Player) {
        this.player.showPlayer(plugin, player)
    }

    fun canSee(player: Player): Boolean {
        return this.player.canSee(player)
    }

    fun isFlying(): Boolean {
        return player.isFlying
    }

    fun setFlying(b: Boolean) {
        player.isFlying = b
    }

    fun setFlySpeed(v: Float) {
        player.flySpeed = v
    }

    fun setWalkSpeed(v: Float) {
        player.walkSpeed = v
    }

    fun getFlySpeed(): Float {
        return player.flySpeed
    }

    fun getWalkSpeed(): Float {
        return player.walkSpeed
    }

    fun setTexturePack(s: String) {
        player.setTexturePack(s)
    }

    fun setResourcePack(s: String) {
        player.setResourcePack(s)
    }

    fun setResourcePack(s: String, bytes: ByteArray?) {
        player.setResourcePack(s, bytes)
    }

    fun getScoreboard(): Scoreboard {
        return player.scoreboard
    }

    fun setScoreboard(scoreboard: Scoreboard) {
        player.scoreboard = scoreboard
    }

    fun isHealthScaled(): Boolean {
        return player.isHealthScaled
    }

    fun setHealthScaled(b: Boolean) {
        player.isHealthScaled = b
    }

    fun setHealthScale(v: Double) {
        player.healthScale = v
    }

    fun getHealthScale(): Double {
        return player.healthScale
    }

    fun getSpectatorTarget(): Entity? {
        return player.spectatorTarget
    }

    fun setSpectatorTarget(entity: Entity?) {
        player.spectatorTarget = entity
    }

    fun sendTitle(s: String, s1: String?, i: Int, i1: Int, i2: Int) {
        player.sendTitle(s, s1, i, i1, i2)
    }

    fun resetTitle() {
        player.resetTitle()
    }

    fun spawnParticle(particle: Particle, location: Location, i: Int) {
        player.spawnParticle(particle, location, i)
    }

    fun spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int) {
        player.spawnParticle(particle, v, v1, v2, i)
    }

    fun <T : Any?> spawnParticle(particle: Particle, location: Location, i: Int, t: T) {
        player.spawnParticle(particle, location, i, t)
    }

    fun <T : Any?> spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int, t: T) {
        player.spawnParticle(particle, v, v1, v2, i, t)
    }

    fun spawnParticle(particle: Particle, location: Location, i: Int, v: Double, v1: Double, v2: Double) {
        player.spawnParticle(particle, location, i, v, v1, v2)
    }

    fun spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int, v3: Double, v4: Double, v5: Double) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5)
    }

    fun <T : Any?> spawnParticle(particle: Particle, location: Location, i: Int, v: Double, v1: Double, v2: Double, t: T) {
        player.spawnParticle(particle, location, i, v, v1, v2, t)
    }

    fun <T : Any?> spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int, v3: Double, v4: Double, v5: Double, t: T) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t)
    }

    fun spawnParticle(particle: Particle, location: Location, i: Int, v: Double, v1: Double, v2: Double, v3: Double) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3)
    }

    fun spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int, v3: Double, v4: Double, v5: Double, v6: Double) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6)
    }

    fun <T : Any?> spawnParticle(particle: Particle, location: Location, i: Int, v: Double, v1: Double, v2: Double, v3: Double, t: T) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3, t)
    }

    fun <T : Any?> spawnParticle(particle: Particle, v: Double, v1: Double, v2: Double, i: Int, v3: Double, v4: Double, v5: Double, v6: Double, t: T) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t)
    }

    fun getAdvancementProgress(advancement: Advancement): AdvancementProgress {
        return player.getAdvancementProgress(advancement)
    }

    fun getLocale(): String {
        return player.locale
    }

    fun spigot(): Player.Spigot {
        return player.spigot()
    }

    fun isOnline(): Boolean {
        return player.isOnline
    }

    fun isBanned(): Boolean {
        return player.isBanned
    }

    fun isWhitelisted(): Boolean {
        return player.isWhitelisted
    }

    fun setWhitelisted(b: Boolean) {
        player.isWhitelisted = b
    }

    fun getPlayer(): Player? {
        return player.player
    }

    fun getFirstPlayed(): Long {
        return player.firstPlayed
    }

    fun getLastPlayed(): Long {
        return player.lastPlayed
    }

    fun hasPlayedBefore(): Boolean {
        return player.hasPlayedBefore()
    }

    fun serialize(): MutableMap<String, Any> {
        return player.serialize()
    }

    fun isConversing(): Boolean {
        return player.isConversing
    }

    fun acceptConversationInput(s: String) {
        player.acceptConversationInput(s)
    }

    fun beginConversation(conversation: Conversation): Boolean {
        return player.beginConversation(conversation)
    }

    fun abandonConversation(conversation: Conversation) {
        player.abandonConversation(conversation)
    }

    fun abandonConversation(conversation: Conversation, conversationAbandonedEvent: ConversationAbandonedEvent) {
        player.abandonConversation(conversation, conversationAbandonedEvent)
    }

    fun getName(): String {
        return player.name
    }

    fun getInventory(): PlayerInventory {
        return player.inventory
    }

    fun getEnderChest(): Inventory {
        return player.enderChest
    }

    fun getMainHand(): MainHand {
        return player.mainHand
    }

    fun setWindowProperty(property: InventoryView.Property, i: Int): Boolean {
        return player.setWindowProperty(property, i)
    }

    fun getOpenInventory(): InventoryView {
        return player.openInventory
    }

    fun openInventory(inventory: Inventory): InventoryView? {
        return player.openInventory(inventory)
    }

    fun openWorkbench(location: Location, b: Boolean): InventoryView? {
        return player.openWorkbench(location, b)
    }

    fun openEnchanting(location: Location, b: Boolean): InventoryView? {
        return player.openEnchanting(location, b)
    }

    fun openInventory(inventoryView: InventoryView) {
        player.openInventory(inventoryView)
    }

    fun openMerchant(villager: Villager, b: Boolean): InventoryView? {
        return player.openMerchant(villager, b)
    }

    fun openMerchant(merchant: Merchant, b: Boolean): InventoryView? {
        return player.openMerchant(merchant, b)
    }

    fun closeInventory() {
        player.closeInventory()
    }

    fun getItemInHand(): ItemStack? {
        return player.itemInHand
    }


    fun getItemOnCursor(): ItemStack? {
        return player.itemOnCursor
    }


    fun hasCooldown(material: Material): Boolean {
        return player.hasCooldown(material)
    }

    fun getCooldown(material: Material): Int {
        return player.getCooldown(material)
    }

    fun setCooldown(material: Material, i: Int) {
        player.setCooldown(material, i)
    }

    fun isSleeping(): Boolean {
        return player.isSleeping
    }

    fun getSleepTicks(): Int {
        return player.sleepTicks
    }

    fun getGameMode(): GameMode {
        return player.gameMode
    }

    fun setGameMode(gameMode: GameMode) {
        player.gameMode = gameMode
    }

    fun isBlocking(): Boolean {
        return player.isBlocking
    }

    fun isHandRaised(): Boolean {
        return player.isHandRaised
    }

    fun getExpToLevel(): Int {
        return player.expToLevel
    }

    fun getShoulderEntityLeft(): Entity? {
        return player.shoulderEntityLeft
    }

    fun setShoulderEntityLeft(entity: Entity?) {
        player.shoulderEntityLeft = entity
    }

    fun getShoulderEntityRight(): Entity? {
        return player.shoulderEntityRight
    }

    fun setShoulderEntityRight(entity: Entity?) {
        player.shoulderEntityRight = entity
    }

    fun getEyeHeight(): Double {
        return player.eyeHeight
    }

    fun getEyeHeight(b: Boolean): Double {
        return player.getEyeHeight(b)
    }

    fun getEyeLocation(): Location {
        return player.eyeLocation
    }

    fun getTargetBlock(set: Set<Material>, i: Int): Block {
        return player.getTargetBlock(set, i)
    }

    fun getLastTwoTargetBlocks(set: Set<Material>, i: Int): List<Block> {
        return player.getLastTwoTargetBlocks(set, i)
    }

    fun getRemainingAir(): Int {
        return player.remainingAir
    }

    fun setRemainingAir(i: Int) {
        player.remainingAir = i
    }

    fun getMaximumAir(): Int {
        return player.maximumAir
    }

    fun setMaximumAir(i: Int) {
        player.maximumAir = i
    }

    fun getMaximumNoDamageTicks(): Int {
        return player.maximumNoDamageTicks
    }

    fun setMaximumNoDamageTicks(i: Int) {
        player.maximumNoDamageTicks = i
    }

    fun getLastDamage(): Double {
        return player.lastDamage
    }

    fun setLastDamage(v: Double) {
        player.lastDamage = v
    }

    fun getNoDamageTicks(): Int {
        return player.noDamageTicks
    }

    fun setNoDamageTicks(i: Int) {
        player.noDamageTicks = i
    }

    fun getKiller(): Player? {
        return player.killer
    }

    fun addPotionEffect(potionEffect: PotionEffect): Boolean {
        return player.addPotionEffect(potionEffect)
    }

    fun addPotionEffect(potionEffect: PotionEffect, b: Boolean): Boolean {
        return player.addPotionEffect(potionEffect, b)
    }

    fun addPotionEffects(collection: Collection<PotionEffect>): Boolean {
        return player.addPotionEffects(collection)
    }

    fun hasPotionEffect(potionEffectType: PotionEffectType): Boolean {
        return player.hasPotionEffect(potionEffectType)
    }

    fun getPotionEffect(potionEffectType: PotionEffectType): PotionEffect? {
        return player.getPotionEffect(potionEffectType)
    }

    fun removePotionEffect(potionEffectType: PotionEffectType) {
        player.removePotionEffect(potionEffectType)
    }

    fun getActivePotionEffects(): Collection<PotionEffect> {
        return player.activePotionEffects
    }

    fun hasLineOfSight(entity: Entity): Boolean {
        return player.hasLineOfSight(entity)
    }

    fun getRemoveWhenFarAway(): Boolean {
        return player.removeWhenFarAway
    }

    fun setRemoveWhenFarAway(b: Boolean) {
        player.removeWhenFarAway = b
    }

    fun getEquipment(): EntityEquipment? {
        return player.equipment
    }

    fun setCanPickupItems(b: Boolean) {
        player.canPickupItems = b
    }

    fun getCanPickupItems(): Boolean {
        return player.canPickupItems
    }

    fun isLeashed(): Boolean {
        return player.isLeashed
    }

    fun getLeashHolder(): Entity? {
        return player.leashHolder
    }

    fun setLeashHolder(entity: Entity?): Boolean {
        return player.setLeashHolder(entity)
    }

    fun isGliding(): Boolean {
        return player.isGliding
    }

    fun setGliding(b: Boolean) {
        player.isGliding = b
    }

    fun setAI(b: Boolean) {
        player.setAI(b)
    }

    fun hasAI(): Boolean {
        return player.hasAI()
    }

    fun setCollidable(b: Boolean) {
        player.isCollidable = b
    }

    fun isCollidable(): Boolean {
        return player.isCollidable
    }

    fun getAttribute(attribute: Attribute): AttributeInstance? {
        return player.getAttribute(attribute)
    }

    fun damage(v: Double) {
        player.damage(v)
    }

    fun damage(v: Double, entity: Entity?) {
        player.damage(v, entity)
    }

    fun getHealth(): Double {
        return player.health
    }

    fun setHealth(v: Double) {
        player.health = v
    }

    fun getMaxHealth(): Double {
        return player.maxHealth
    }

    fun setMaxHealth(v: Double) {
        player.maxHealth = v
    }

    fun resetMaxHealth() {
        player.resetMaxHealth()
    }

    fun getLocation(): Location {
        return player.location
    }

    fun getLocation(location: Location): Location {
        return player.getLocation(location)!!
    }

    fun setVelocity(vector: Vector) {
        player.velocity = vector
    }

    fun getVelocity(): Vector {
        return player.velocity
    }

    fun getHeight(): Double {
        return player.height
    }

    fun getWidth(): Double {
        return player.width
    }

    fun isOnGround(): Boolean {
        return player.isOnGround
    }

    fun getWorld(): World {
        return player.world
    }

    fun teleport(location: Location): Boolean {
        return player.teleport(location)
    }

    fun teleport(location: Location, teleportCause: PlayerTeleportEvent.TeleportCause): Boolean {
        return player.teleport(location, teleportCause)
    }

    fun teleport(entity: Entity): Boolean {
        return player.teleport(entity)
    }

    fun teleport(entity: Entity, teleportCause: PlayerTeleportEvent.TeleportCause): Boolean {
        return player.teleport(entity, teleportCause)
    }

    fun getNearbyEntities(v: Double, v1: Double, v2: Double): List<Entity> {
        return player.getNearbyEntities(v, v1, v2)
    }

    fun getEntityId(): Int {
        return player.entityId
    }

    fun getFireTicks(): Int {
        return player.fireTicks
    }

    fun getMaxFireTicks(): Int {
        return player.maxFireTicks
    }

    fun setFireTicks(i: Int) {
        player.fireTicks = i
    }

    fun remove() {
        player.remove()
    }

    fun isDead(): Boolean {
        return player.isDead
    }

    fun isValid(): Boolean {
        return player.isValid
    }

    fun getServer(): Server {
        return player.server
    }

    fun getPassenger(): Entity? {
        return player.passenger
    }

    fun setPassenger(entity: Entity?): Boolean {
        return entity?.let { player.setPassenger(it) } == true
    }

    fun getPassengers(): List<Entity> {
        return player.passengers
    }

    fun addPassenger(entity: Entity): Boolean {
        return player.addPassenger(entity)
    }

    fun removePassenger(entity: Entity): Boolean {
        return player.removePassenger(entity)
    }

    fun isEmpty(): Boolean {
        return player.isEmpty
    }

    fun eject(): Boolean {
        return player.eject()
    }

    fun getFallDistance(): Float {
        return player.fallDistance
    }

    fun setFallDistance(v: Float) {
        player.fallDistance = v
    }



    fun getLastDamageCause(): EntityDamageEvent? {
        return player.lastDamageCause
    }

    fun getUniqueId(): UUID {
        return player.uniqueId
    }

    fun getTicksLived(): Int {
        return player.ticksLived
    }

    fun setTicksLived(i: Int) {
        player.ticksLived = i
    }

    fun playEffect(entityEffect: EntityEffect) {
        player.playEffect(entityEffect)
    }

    fun getType(): EntityType {
        return player.type
    }

    fun isInsideVehicle(): Boolean {
        return player.isInsideVehicle
    }

    fun leaveVehicle(): Boolean {
        return player.leaveVehicle()
    }

    fun getVehicle(): Entity? {
        return player.vehicle
    }

    fun setCustomNameVisible(b: Boolean) {
        player.isCustomNameVisible = b
    }

    fun isCustomNameVisible(): Boolean {
        return player.isCustomNameVisible
    }

    fun setGlowing(b: Boolean) {
        player.isGlowing = b
    }

    fun isGlowing(): Boolean {
        return player.isGlowing
    }

    fun setInvulnerable(b: Boolean) {
        player.isInvulnerable = b
    }

    fun isInvulnerable(): Boolean {
        return player.isInvulnerable
    }

    fun isSilent(): Boolean {
        return player.isSilent
    }

    fun setSilent(b: Boolean) {
        player.isSilent = b
    }

    fun hasGravity(): Boolean {
        return player.hasGravity()
    }

    fun setGravity(b: Boolean) {
        player.setGravity(b)
    }

    fun getPortalCooldown(): Int {
        return player.portalCooldown
    }

    fun setPortalCooldown(i: Int) {
        player.portalCooldown = i
    }

    fun getScoreboardTags(): MutableSet<String> {
        return player.scoreboardTags
    }

    fun addScoreboardTag(s: String): Boolean {
        return player.addScoreboardTag(s)
    }

    fun removeScoreboardTag(s: String): Boolean {
        return player.removeScoreboardTag(s)
    }

    fun getPistonMoveReaction(): PistonMoveReaction {
        return player.pistonMoveReaction
    }

    fun getCustomName(): String? {
        return player.customName
    }

    fun setCustomName(s: String?) {
        player.customName = s
    }

    fun setMetadata(s: String, metadataValue: MetadataValue) {
        player.setMetadata(s, metadataValue)
    }

    fun getMetadata(s: String): List<MetadataValue> {
        return player.getMetadata(s)
    }

    fun hasMetadata(s: String): Boolean {
        return player.hasMetadata(s)
    }

    fun removeMetadata(s: String, plugin: Plugin) {
        player.removeMetadata(s, plugin)
    }

    fun isPermissionSet(s: String): Boolean {
        return player.isPermissionSet(s)
    }

    fun isPermissionSet(permission: Permission): Boolean {
        return player.isPermissionSet(permission)
    }

    fun hasPermission(s: String): Boolean {
        return player.hasPermission(s)
    }

    fun hasPermission(permission: Permission): Boolean {
        return player.hasPermission(permission)
    }

    fun addAttachment(plugin: Plugin, s: String, b: Boolean): PermissionAttachment {
        return player.addAttachment(plugin, s, b)
    }

    fun addAttachment(plugin: Plugin): PermissionAttachment {
        return player.addAttachment(plugin)
    }


    fun removeAttachment(permissionAttachment: PermissionAttachment) {
        player.removeAttachment(permissionAttachment)
    }

    fun recalculatePermissions() {
        player.recalculatePermissions()
    }

    fun getEffectivePermissions(): MutableSet<PermissionAttachmentInfo> {
        return player.effectivePermissions
    }

    fun isOp(): Boolean {
        return player.isOp
    }

    fun setOp(b: Boolean) {
        player.isOp = b
    }

    fun sendPluginMessage(plugin: Plugin, s: String, bytes: ByteArray) {
        player.sendPluginMessage(plugin, s, bytes)
    }

    fun getListeningPluginChannels(): MutableSet<String> {
        return player.listeningPluginChannels
    }

    fun <T : Projectile?> launchProjectile(aClass: Class<out T>): T {
        return player.launchProjectile(aClass)
    }

    fun <T : Projectile?> launchProjectile(aClass: Class<out T>, vector: Vector): T {
        return player.launchProjectile(aClass, vector)
    }
}
